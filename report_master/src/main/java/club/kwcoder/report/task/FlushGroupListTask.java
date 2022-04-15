package club.kwcoder.report.task;

import club.kwcoder.report.model.task.GroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 定时任务：
 * 每五分钟获取账号内的群组列表
 * 【注意】此代码后期将更新，进行数据库中的群组列表的更新
 */
@Component
public class FlushGroupListTask {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 300000)
    public void friendListFlushTask() {
        String url = "http://localhost:5700/get_group_list";
        ResponseEntity<GroupModel> getGroupList = restTemplate.getForEntity(url, GroupModel.class);
        if (getGroupList.hasBody()) {
            GroupModel body = getGroupList.getBody();
            List<GroupModel.Group> data = body.getData();
            data.forEach(System.out::println);
        }
    }

}
