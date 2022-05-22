package club.kwcoder.report.task.init;

import club.kwcoder.report.config.mq.consumer.CustomConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(value = 0)
public class MessageTask implements CommandLineRunner {

    @Autowired
    private CustomConsumer consumer;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    /**
     * 从消息队列中获取消息并发送
     * 若发送失败，则将消息重新push回队列中
     *     // channel wanxiao_report
     */
    @Override
    public void run(String... args) {
        new Thread(() -> {
            try {
                consumer.consumer(routingKey);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}



