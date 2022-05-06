package club.kwcoder.report;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ReportApplicationTests {

    @Test
    void contextLoads() {

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        String str = String.format("%s.%s.%s", tomorrow.getYear(), tomorrow.getMonthValue(), tomorrow.getDayOfMonth());
        System.out.println(str);

    }
}
