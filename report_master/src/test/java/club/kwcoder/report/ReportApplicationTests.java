package club.kwcoder.report;

import club.kwcoder.report.dataobject.ReportTime;
import club.kwcoder.report.dataobject.ReportTimeExample;
import club.kwcoder.report.mapper.ReportTimeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ReportApplicationTests {

    @Autowired
    private ReportTimeDao reportTimeDao;

    @Test
    void contextLoads() {
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);

        int hour = now.getHour();
        int minute = now.getMinute();

        int time = hour * 2 + (minute < 29 ? 1 : 2);

        ReportTimeExample reportTimeExample = new ReportTimeExample();
        reportTimeExample.createCriteria().andTimeEqualTo(time);
        List<ReportTime> reportTimes = reportTimeDao.selectByExample(reportTimeExample);

        System.out.println(reportTimes);
    }

}
