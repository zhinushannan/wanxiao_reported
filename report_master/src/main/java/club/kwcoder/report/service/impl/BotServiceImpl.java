package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.*;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.BotDTO;
import club.kwcoder.report.model.dto.BotInsertDTO;
import club.kwcoder.report.model.dto.GroupDTO;
import club.kwcoder.report.model.dto.LogDTO;
import club.kwcoder.report.service.BotService;
import club.kwcoder.report.utils.BotUtil;
import club.kwcoder.report.utils.RedisUtil;
import club.kwcoder.report.utils.StreamCloseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private BotDao botDao;

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BotUtil botUtil;

    @Override
    public ResultBean<PageBean<BotDTO>> list(PageBean<BotDTO> pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Bot> bots = botDao.selectByExample(new BotExample());
        PageInfo<Bot> pageInfo = PageInfo.of(bots);

        List<BotDTO> botDTOS = new ArrayList<>();

        bots.forEach(bot -> {
            ClazzExample example = new ClazzExample();
            example.createCriteria().andBotPortEqualTo(String.valueOf(bot.getPort()));

            List<String> clazz = new ArrayList<>();
            clazzDao.selectByExample(example).forEach(c -> clazz.add(c.getClazzName()));
            botDTOS.add(new BotDTO()
                    .setBotId(bot.getBotId())
                    .setPort(bot.getPort())
                    .setStatus(bot.getStatus())
                    .setClazz(clazz)
            );
        });

        pageBean
                .setTotal(pageInfo.getTotal())
                .setData(botDTOS);

        return ResultBean.ok("Bots查询成功！", pageBean);
    }

    @Override
    public ResultBean<List<Bot>> list() {
        List<Bot> bots = botDao.selectByExample(new BotExample());
        return ResultBean.ok("Bots查询成功！", bots);
    }

    @Override
    public ResultBean<List<LogDTO>> logs(String port, String sessionId) {
        String lastTime;

        if (StringUtils.isBlank(sessionId)) {
            lastTime = null;
            sessionId = UUID.randomUUID().toString();
        } else {
            lastTime = redisUtil.getString("log:" + sessionId + ":" + port);
        }

        if (StringUtils.isBlank(lastTime)) {
            lastTime = "[" + LocalDateTime.now().minusMinutes(5).toString().replace("T", " ").split("\\.")[0] + "]";
        }

        String currentLogPath = botUtil.getCurrentLogPath(port);
        List<LogDTO> logs = new ArrayList<>();
        RandomAccessFile rf;
        try {
            rf = new RandomAccessFile(currentLogPath, "r");
            long len = rf.length() - 1; // 此处 -1 可以防止最后一行是空行而报错

            long nextend = len - 1;
            String line;
            rf.seek(nextend);
            int c;
            while (nextend >= 0) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    String s = rf.readLine();
                    line = new String(s.getBytes(StandardCharsets.ISO_8859_1));
                    LogDTO logDTO = this.parseToDto(line);
                    // 前者大则为正，为正即当前获取的时间大于上次获取的时间，即此条日志上次没获取，因此这次需要获取
                    if (logDTO.getTime().compareTo(lastTime) > 0) {
                        logs.add(0, logDTO);
                    }
                }

                if (nextend == 0) {
                    rf.seek(0);
                    line = new String(rf.readLine().getBytes(StandardCharsets.ISO_8859_1));
                    LogDTO logDTO = this.parseToDto(line);
                    if (logDTO.getTime().compareTo(lastTime) > 0) {
                        logs.add(0, logDTO);
                    }
                } else {
                    rf.seek(nextend - 1);//为下一次循环做准备
                }
                nextend--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (logs.size() != 0) {
            redisUtil.setString("log:" + sessionId + ":" + port, logs.get(logs.size() - 1).getTime());
        }

        return ResultBean.ok(sessionId, logs);
    }

    @Override
    public ResultBean<Integer> getAvailablePort(Integer port) {
        BotExample example = new BotExample();

        boolean flag = false;
        if (port != null && port >= 5700) {
            example.createCriteria().andPortEqualTo(port);
            List<Bot> bots = botDao.selectByExample(example);
            example.clear();
            if (bots.size() == 0) {
                flag = true;
            }
        }

        if (port == null || !flag) {
            example.setOrderByClause("port desc");
            PageHelper.startPage(1, 1);
            Bot bot = botDao.selectByExample(example).get(0);
            port = bot.getPort() + 1;
        }

        return ResultBean.ok("查询成功！", port);

    }

    @Override
    public ResultBean<String> add(BotInsertDTO bot) {
        String templatePath = botUtil.getTemplatePath();
        String botPath = botUtil.getBotPath(String.valueOf(bot.getServersHttpPort()));

        File src = new File(templatePath + "/go-cqhttp");
        File tar = new File(botPath);
        if (!tar.exists()) {
            @SuppressWarnings("unused") boolean mkdirs = tar.mkdirs();
        }
        String movePath = tar + File.separator + src.getName();
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(src));
            outputStream = new BufferedOutputStream(new FileOutputStream(movePath));
            byte[] b = new byte[1024];
            int temp;
            while ((temp = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, temp);
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamCloseUtil.close(inputStream, outputStream);
        }

        File ftl = new File(templatePath);
        File yml = new File(botPath + "/config.yml");
        Writer out = null;
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            //指定模板文件的来源
            cfg.setDirectoryForTemplateLoading(ftl);
            //这是模板的编码
            cfg.setDefaultEncoding("UTF-8");
            //获取模板
            Template template = cfg.getTemplate("config.ftl");
            //创建FreeMarker的数据模型
            Map<String, String> root = new HashMap<>();
            root.put("uin", bot.getAccountUin());
            root.put("password", bot.getAccountPassword());
            root.put("port", String.valueOf(bot.getServersHttpPort()));
            //这是输出文件
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(yml)));
            //将模板与数据模型合并
            template.process(root, out);
            // 执行
            this.restart(String.valueOf(bot.getServersHttpPort()));
        } catch (IOException | TemplateException ignored) {
            // TODO 错误处理：删除相关的文件
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ignored) {
                }
            }
        }

        // TODO 添加进数据库

        // TODO 通过修改日志等级可以获取日志

        return ResultBean.ok("添加成功，请不要离开界面，稍后会弹出登录二维码！", null);
    }

    @Override
    public ResultBean<String> qrcode(String port) {
        String botPath = botUtil.getBotPath(port);
        String path = botPath + "/qrcode.png";
        File qrcode = new File(path);

        if (!qrcode.exists()) {
            return ResultBean.ok("false", "");
        }

        InputStream inputStream;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(qrcode);
            bytes = new byte[inputStream.available()];
            @SuppressWarnings("unused") int read = inputStream.read(bytes);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(bytes);

        return ResultBean.ok("true", base64);
    }

    @Override
    public ResultBean<List<GroupDTO>> groupList(String botId, String port) {
        ClazzExample clazzExample = new ClazzExample();
        clazzExample.createCriteria().andBotPortEqualTo(port);

        List<String> groupIds = botUtil.getGroupList(port);

        List<GroupDTO> groupLists = new ArrayList<>();


        // TODO 检查是否在群的方式
//        clazzDao.selectByExample(clazzExample).forEach(clazz -> {
//            GroupList groupList = groupListDao.selectByPrimaryKey(new GroupListKey(botId, clazz.getGroupId()));
//            if (groupList == null) {
//                groupLists.add(new GroupDTO().setGroupId(clazz.getGroupId())
//                        .setClazzName(clazz.getClazzName())
//                        .setIsMember(groupIds.contains(clazz.getGroupId())));
//            } else {
//                groupLists.add(new GroupDTO().setGroupId(groupList.getGroupId())
//                        .setClazzName(clazz.getClazzName())
//                        .setMark(groupList.getMark())
//                        .setIsMember(groupIds.contains(groupList.getGroupId())));
//            }
//        });

        return ResultBean.ok("查询成功！", groupLists);
    }

    @Override
    public ResultBean<String> restart(String port) {
        String botPath = botUtil.getBotPath(port);
        try {
            String command = "chmod +x ./go-cqhttp";
            Runtime.getRuntime().exec(command, new String[]{}, new File(botPath));
            command = "nohup ./go-cqhttp & ";
            Runtime.getRuntime().exec(command, new String[]{}, new File(botPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO 启动失败的情况
        // TODO 启动成功后刷新机器人状态
        return ResultBean.ok("启动成功！", null);
    }


    private LogDTO parseToDto(String logStr) {
        {
            // 匹配时间
            String pattern = "\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}]";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(logStr);
            @SuppressWarnings("unused") boolean b1 = m.find();
            String times = m.group();

            // 匹配等级
            pattern = "\\[[A-Z]+]";
            r = Pattern.compile(pattern);
            m = r.matcher(logStr);
            @SuppressWarnings("unused") boolean b2 = m.find();
            String level = m.group();

            // 匹配内容
            int start = m.end() + 2;
            String log = logStr.substring(start);

            return new LogDTO()
                    .setTime(times)
                    .setLevel(level)
                    .setContent(log);
        }
    }

}
