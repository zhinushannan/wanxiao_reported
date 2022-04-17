package club.kwcoder.report.config.mq.consumer;

import club.kwcoder.report.config.mq.RabbitmqConfig;
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
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomConsumer {

    @Autowired
    private RabbitmqConfig rabbitmqConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Bean
    public Channel getChannel() {
        return rabbitmqConfig.getConnectionFactory().createConnection().createChannel(true);
    }

    public void consumer(String queue) throws IOException {
        Connection connection = rabbitmqConfig.getConnectionFactory().createConnection();
        Channel channel = connection.createChannel(true);
        channel.queueDeclare(routingKey, true, false, false, null);
        channel.basicConsume(queue, true, new MyConsumer(channel));
    }


    class MyConsumer extends DefaultConsumer {

        public MyConsumer(Channel channel) {
            super(channel);
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
            Message message = JSON.parseObject(new String(body), Message.class);

            if (message.getDelete()) {
                System.out.println("message_id:" + message.getGroupId());
                String messageId = redisUtil.getString("message_id:" + message.getGroupId());
                String deleteUrl = String.format("http://127.0.0.1:%s/delete_msg?message_id=%s", message.getPort(), messageId);
                restTemplate.getForEntity(deleteUrl, Object.class);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String sendUrl = String.format("http://127.0.0.1:%s/send_group_msg?group_id=%s&message=%s", message.getPort(), message.getGroupId(), message.getMsg());
            ResponseEntity<Map> forEntity = restTemplate.getForEntity(sendUrl, Map.class);
            System.out.println("===============forEntity===============");
            if (forEntity.getBody() != null) {
                if (StringUtils.equals("failed", forEntity.getBody().get("status").toString())) {
                    // 发送失败，再添加回队列尾部
                    rabbitTemplate.convertAndSend("hello", body);
                } else {
                    if (message.getDelete()) {
                        Map data = (Map) forEntity.getBody().get("data");
                        String message_id = data.get("message_id").toString();
                        redisUtil.setString("message_id:" + message.getGroupId(), message_id);
                    }
                }
            } else {
                rabbitTemplate.convertAndSend(routingKey, body);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@Data
class Message {
    private String groupId;
    private String msg;
    private Boolean delete;
    private String port;
}