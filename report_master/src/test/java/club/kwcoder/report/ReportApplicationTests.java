package club.kwcoder.report;

import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.LogDTO;
import club.kwcoder.report.service.BotService;
import club.kwcoder.report.utils.BotUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
class ReportApplicationTests {

    @Autowired
    private BotService botService;

    @Test
    void contextLoads() {
        String a = "[2022-05-12 19:47:46]";
        String b = "[2022-05-12 19:46:59]";
        System.out.println(a.compareTo(b));
    }

}
