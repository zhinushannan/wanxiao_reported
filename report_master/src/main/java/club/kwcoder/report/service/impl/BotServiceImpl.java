package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.BotExample;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.BotDTO;
import club.kwcoder.report.model.dto.LogDTO;
import club.kwcoder.report.service.BotService;
import club.kwcoder.report.utils.RedisUtil;
import club.kwcoder.report.utils.StreamCloseUtil;
import club.kwcoder.report.utils.YamlUtil;
import com.github.pagehelper.PageHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private RedisUtil redisUtil;

    @Value("${path.bot.templates}")
    private String botTemplatesPath;

    @Value("${path.bot.app}")
    private String botAppPath;

    @Override
    public ResultBean<List<Bot>> list() {
        List<Bot> bots = botDao.selectByExample(new BotExample());
        return ResultBean.ok("Bots查询成功！", bots);
    }

    @Override
    public ResultBean<List<LogDTO>> logs(String botId, String sessionId) {
        String lastTime;

        if (StringUtils.isBlank(sessionId)) {
            lastTime = redisUtil.getString("log:" + sessionId + ":" + botId);
        } else {
            lastTime = null;
        }

        if (StringUtils.isBlank(lastTime)) {
            lastTime = "[" + LocalDateTime.now().minusMinutes(5).toString().replace("T", " ").split("\\.")[0] + "]";
        }

        List<LogDTO> logs = new ArrayList<>();
        RandomAccessFile rf;
        try {
            rf = new RandomAccessFile("/home/zhinushannan/Desktop/small-nohup.out", "r");
            long len = rf.length();

            long nextend = len - 1;
            String line;
            rf.seek(nextend);
            int c;
            while (nextend >= 0) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = new String(rf.readLine().getBytes(StandardCharsets.ISO_8859_1));
                    try {
                        LogDTO logDTO = this.parseToDto(line);
                        if (!StringUtils.equals(logDTO.getTime(), lastTime)) {
                            logs.add(0, logDTO);
                        }
                    } catch (IllegalStateException ignored) {
                    }
                }

                if (nextend == 0) {
                    rf.seek(0);
                    line = new String(rf.readLine().getBytes(StandardCharsets.ISO_8859_1));
                    try {
                        LogDTO logDTO = this.parseToDto(line);
                        if (!StringUtils.equals(logDTO.getTime(), lastTime)) {
                            logs.add(0, logDTO);
                        }
                    } catch (IllegalStateException ignored) {
                    }
                } else {
                    rf.seek(nextend - 1);//为下一次循环做准备
                }
                nextend--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        redisUtil.setString("log:" + sessionId + ":" + botId, logs.get(logs.size() - 1).getTime());

        return ResultBean.ok("sessionId", logs);
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
    public ResultBean<String> add(BotDTO bot) {
        File src = new File(botTemplatesPath);
        File tar = new File(botAppPath + bot.getServersHttpPort());
        if (!tar.exists()) {
            @SuppressWarnings("unused") boolean mkdirs = tar.mkdirs();
        }
        File[] files = src.listFiles();
        assert files != null;
        for (File file : files) {
            String movePath = tar + File.separator + file.getName();
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(new FileOutputStream(movePath));
                byte[] b = new byte[1024];
                int temp;
                while ((temp = in.read(b)) != -1) {
                    out.write(b, 0, temp);
                }
            } catch (FileNotFoundException ignored) {
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamCloseUtil.close(in, out);
            }
        }

        String fltPath = "";
        String ymlPath = "";
        Writer out = null;
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            //指定模板文件的来源
            cfg.setDirectoryForTemplateLoading(new File(fltPath));
            //这是模板的编码
            cfg.setDefaultEncoding("UTF-8");
            //获取模板
            Template template = cfg.getTemplate("config.ftl");
            //创建FreeMarker的数据模型
            Map<String,String> root = new HashMap<>();
            root.put("uin","freemarker");
            root.put("password","freemarker");
            root.put("port","freemarker");
            //这是输出文件
            File file = new File(ymlPath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            //将模板与数据模型合并
            template.process(root, out);
        } catch (IOException | TemplateException ignored) {
            // 错误处理
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ignored) {}
            }
        }



        return null;
    }


    private LogDTO parseToDto(String logStr) {
        {
            // 匹配时间
            String pattern = "\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}]";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(logStr);
            @SuppressWarnings("unused") boolean b1 =  m.find();
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
