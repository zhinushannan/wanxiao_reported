package club.kwcoder.report.task;

import club.kwcoder.report.model.task.FriendModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 定时任务：
 * 每五分钟获取账号内的好友列表
 * 【注意】此代码后期将更新，进行数据库中的好友列表的更新
 */
@Component
public class FlushFriendListTask {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 300000)
    public void friendListFlushTask() {
        String url = "http://localhost:5700/get_friend_list";
        ResponseEntity<FriendModel> getFriendList = restTemplate.getForEntity(url, FriendModel.class);
        if (getFriendList.hasBody()) {
            FriendModel body = getFriendList.getBody();
            List<FriendModel.Friend> data = body.getData();
            data.forEach(System.out::println);
        }
    }

}
