package club.kwcoder.report.task.schedule;

import club.kwcoder.report.dataobject.*;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.service.ListFlush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务：
 * 刷新机器人的群组、好友列表，每十分钟一次
 */
@Component
public class FLushListTask {

    @Autowired
    private ListFlush listFlush;

    @Autowired
    private BotDao botDao;

    /**
     * 先从数据库中获取机器人列表，获取到机器人的qq号(botId)和端口号
     */
    @Scheduled(fixedRate = 600000)
    public void listFlushTask() {
        BotExample botExample = new BotExample();
        botDao.selectByExample(botExample).forEach(bot -> {
            listFlush.groupFlushList(bot.getBotId(), bot.getPort());
            listFlush.friendFlushList(bot.getBotId(), bot.getPort());
        });
    }


}
