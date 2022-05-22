package club.kwcoder.report.task.schedule;

import club.kwcoder.report.dataobject.ReportTime;
import club.kwcoder.report.dataobject.ReportTimeExample;
import club.kwcoder.report.mapper.ReportTimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private ReportTimeDao reportTimeDao;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void scheduled() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int time = hour * 2 + (minute < 29 ? 1 : 2);
        ReportTimeExample reportTimeExample = new ReportTimeExample();
        reportTimeExample.createCriteria().andTimeEqualTo(time);
        List<ReportTime> reportTimes = reportTimeDao.selectByExample(reportTimeExample);
    }

}
