package club.kwcoder.report;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ReportApplicationTests {

    @Test
    void contextLoads() throws IOException, TemplateException {
        String path = "/home/zhinushannan/CODE/wanxiao_reported/go-cqhttp/templates/";

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //指定模板文件的来源
        cfg.setDirectoryForTemplateLoading(new File(path));
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
        File file = new File(path + "/5701/config.yml");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        //将模板与数据模型合并
        template.process(root, out);
        out.flush();
        out.close();


    }


}
