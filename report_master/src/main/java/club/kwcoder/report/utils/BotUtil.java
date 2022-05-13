package club.kwcoder.report.utils;

import club.kwcoder.report.model.task.GroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BotUtil {

    @Value("${path.bot.templates}")
    private String TEMPLATE_PATH;

    @Value("${path.bot.app}")
    private String botAppPath;

    @Autowired
    private RestTemplate restTemplate;

    public String getTemplatePath() {
        return this.TEMPLATE_PATH;
    }

    public String getBotPath(String port) {
        return botAppPath + port + "/";
    }

    public String getCurrentLogPath(String port) {
        return this.getBotPath(port) + "logs/" + LocalDate.now() + ".log";
    }

    public List<String> getGroupList(String port) {
        List<String> groupIds = new ArrayList<>();

        String url = "http://localhost:" + port + "/get_group_list";
        ResponseEntity<GroupModel> groupList = restTemplate.getForEntity(url, GroupModel.class);
        if (groupList.getBody() != null) {
            GroupModel body = groupList.getBody();
            body.getData().forEach(group -> groupIds.add(group.getGroup_id()));
        }
        return groupIds;
    }

}
