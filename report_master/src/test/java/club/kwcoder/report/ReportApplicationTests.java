package club.kwcoder.report;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
class ReportApplicationTests {

    @Test
    void contextLoads() throws IOException, InterruptedException {
        Process exec = Runtime.getRuntime().exec("/home/zhinushannan/CODE/wanxiao_reported/go-cqhttp/5701/start.sh");

        exec.waitFor();
        InputStream in = exec.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while((line = read.readLine())!=null){
            System.out.println(line);
        }

    }


}
