package club.kwcoder.report.task.init;

import club.kwcoder.report.config.mq.RabbitmqConfig;
import club.kwcoder.report.config.mq.consumer.CustomConsumer;
import club.kwcoder.report.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

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



