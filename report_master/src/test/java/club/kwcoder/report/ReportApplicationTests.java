package club.kwcoder.report;

import club.kwcoder.report.dataobject.Student;
import com.csvreader.CsvReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SpringBootTest
class ReportApplicationTests {

    @Value("${path.target.excel}")
    private String excelTargetPath;

    @Test
    void contextLoads() {


    }

}
