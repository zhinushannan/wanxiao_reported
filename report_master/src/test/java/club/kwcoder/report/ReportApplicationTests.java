package club.kwcoder.report;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
class ReportApplicationTests {

    @Test
    void contextLoads() throws IOException, InterruptedException {
        Process exec = Runtime.getRuntime().exec("nohup ./go-cqhttp & ", new String[]{}, new File("/home/zhinushannan/Desktop/test"));
        exec.waitFor();
    }

}
