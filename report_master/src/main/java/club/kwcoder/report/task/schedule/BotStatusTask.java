package club.kwcoder.report.task.schedule;

import club.kwcoder.report.dataobject.BotExample;
import club.kwcoder.report.mapper.BotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 定时任务：
 * 每十秒检查一次端口占用（即检测机器人的状态）
 * 若端口不在占用，则说明机器人已经死亡，需要重启
 *
 * 【待完善】：
 * 1、当机器人挂掉的时候用邮件通知管理员
 * 2、机器人挂掉的时候自动重启，并通知管理员重启结果
 *
 */
@Component
public class BotStatusTask {

    @Autowired
    private BotDao botDao;

//    @Scheduled(fixedRate = 10000)
    public void botStatus() {
        BotExample botExample = new BotExample();
        botDao.selectByExample(botExample).forEach(bot -> {
            boolean flag;
            try {
                flag = isPortUsing(bot.getPort());
            } catch (UnknownHostException e) {
                e.printStackTrace();
                flag = false;
            }
            if (!flag && bot.getStatus() == 1) {
                bot.setStatus(0);
                botDao.updateByPrimaryKey(bot);
            }
            if (flag && bot.getStatus() == 0) {
                bot.setStatus(1);
                botDao.updateByPrimaryKey(bot);
            }
        });
    }

    /**
     * 根据IP和端口号，查询其是否被占用
     * 实现方法：
     * 尝试与 host:port 建立通信，如果可以建立，则说明端口没有被占用
     * 若端口占用，则直接抛出异常
     *
     * @param port 端口号
     * @return 如果被占用，返回true；否则返回false
     * @throws UnknownHostException IP地址不通或错误，则会抛出此异常
     */
    private boolean isPortUsing(int port) throws UnknownHostException {
        boolean flag = true;
        InetAddress address = InetAddress.getByName("127.0.0.1");
        try {
            Socket socket = new Socket(address, port);
            socket.close();
        } catch (IOException e) {
            flag = false;
            //如果所测试端口号没有被占用，那么会抛出异常，这里利用这个机制来判断
            //所以，这里在捕获异常后，什么也不用做
        }
        return flag;
    }

}
