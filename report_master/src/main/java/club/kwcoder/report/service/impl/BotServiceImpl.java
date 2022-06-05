package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.*;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.*;
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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${os}")
    private String os;

    @Override
    public ResultBean<PageBean<BotDTO>> list(PageBean<BotDTO> pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Bot> bots = botDao.selectByExample(new BotExample());
        PageInfo<Bot> pageInfo = PageInfo.of(bots);

        List<BotDTO> botDTOS = new ArrayList<>();

        bots.forEach(bot -> {
            ClazzExample example = new ClazzExample();
            example.createCriteria().andBotPortEqualTo(bot.getPort());

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
    public ResultBean<List<String>> logs(String port, String sessionId) {
        String lastLog;

        // 当 sessionId 为空，即第一次请求
        if (StringUtils.isBlank(sessionId)) {
            lastLog = null;
            sessionId = UUID.randomUUID().toString();
        } else {
            lastLog = redisUtil.getString("log:" + sessionId + ":" + port);
        }

        String currentLogPath = botUtil.getCurrentLogPath(port);
        List<String> logs = new ArrayList<>();
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
                    // 如果两者不相等，则说明还没获取到上一条日志
                    if (!StringUtils.equals(line, lastLog)) {
                        logs.add(0, line);
                    }
                    // 如果两者相等，则说明已经获取到了指定的日志，需要停止
                    else {
                        break;
                    }
                }

                if (nextend == 0) {
                    rf.seek(0);
                    line = new String(rf.readLine().getBytes(StandardCharsets.ISO_8859_1));
                    if (!StringUtils.equals(line, lastLog)) {
                        logs.add(0, line);
                    } else {
                        break;
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
            redisUtil.setString("log:" + sessionId + ":" + port, logs.get(logs.size() - 1));
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
            List<Bot> bots = botDao.selectByExample(example);
            if (bots.size() > 0) {
                port = bots.get(0).getPort() + 1;
            } else {
                port = 5700;
            }
        }

        return ResultBean.ok("查询成功！", port);

    }

    @Override
    public ResultBean<String> add(BotInsertDTO bot) {
        String templatePath = botUtil.getTemplatePath();
        String botPath = botUtil.getBotPath(String.valueOf(bot.getServersHttpPort()));

        File src = new File(templatePath + String.format("/go-cqhttp-%s", os));
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
            return ResultBean.error("新增失败，请稍后重试！", null);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ignored) {
                }
            }
        }

        botDao.insert(new Bot()
                .setBotId(bot.getAccountUin())
                .setPort(bot.getServersHttpPort())
                .setStatus(0));

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
    public ResultBean<List<GroupDTO>> groupList(String botId, Integer port) {
        ClazzExample clazzExample = new ClazzExample();
        clazzExample.createCriteria().andBotPortEqualTo(port);

        List<String> groupIds = botUtil.getGroupList(port);

        List<GroupDTO> groupLists = new ArrayList<>();

        clazzDao.selectByExample(clazzExample).forEach(clazz -> groupLists.add(
                new GroupDTO()
                        .setGroupId(clazz.getGroupId())
                        .setClazzName(clazz.getClazzName())
                        .setIsMember(groupIds.contains(clazz.getGroupId()))
        ));

        return ResultBean.ok("查询成功！", groupLists);
    }

    @Override
    public ResultBean<String> restart(String port) {
        String botPath = botUtil.getBotPath(port);
        try {
            String command = String.format("chmod +x ./go-cqhttp-%s", os);
            Runtime.getRuntime().exec(command, new String[]{}, new File(botPath));
            command = String.format("nohup ./go-cqhttp-%s & ", os);
            Runtime.getRuntime().exec(command, new String[]{}, new File(botPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
