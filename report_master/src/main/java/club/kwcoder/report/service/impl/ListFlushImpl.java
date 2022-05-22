package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.dataobject.GroupListExample;
import club.kwcoder.report.mapper.GroupListDao;
import club.kwcoder.report.mapper.batch.GroupListBatchDao;
import club.kwcoder.report.model.task.FriendModel;
import club.kwcoder.report.model.task.GroupModel;
import club.kwcoder.report.service.ListFlush;
import club.kwcoder.report.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1、修复添加好友后无法将新好友存入数据库的bug
 * 2、依据同样原理处理群组，理论上没有bug，但未测试
 */
@Service
public class ListFlushImpl implements ListFlush {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GroupListBatchDao groupListBatchDao;

    @Autowired
    private GroupListDao groupListDao;

    @Override
    public void groupFlushList(String botId, int port) {
        String url = "http://localhost:" + port + "/get_group_list";
        ResponseEntity<GroupModel> groupList = restTemplate.getForEntity(url, GroupModel.class);
        if (groupList.getBody() != null) {
            GroupModel body = groupList.getBody();

            // 将原本的群组信息集合转成 groupId : Group 的Map
            Map<String, GroupModel.Group> groupMap = body
                    .getData()
                    .stream()
                    .collect(Collectors.toMap(GroupModel.Group::getGroup_id, group -> group));

            String baseKey = botId + ":group:";
            List<GroupList> addLists = new ArrayList<>();
            List<String> delLists = new ArrayList<>();

            redisUtil.getAllKeys(baseKey).forEach(s -> {
                String groupId = s.split(":")[2];
                GroupModel.Group group = groupMap.get(groupId);
                if (group == null) {
                    redisUtil.del(s);
                    delLists.add(groupId);
                } else {
                    if (!StringUtils.equals(redisUtil.getString(s), group.toString())) {
                        addLists.add(groupTransfer(botId, group));
                    }
                }

                groupMap.remove(groupId);

            });

            if (groupMap.size() != 0) {
                groupMap.forEach((s, group) -> {
                    addLists.add(groupTransfer(botId, group));
                    redisUtil.setString(baseKey + group.getGroup_id(), group.toString());
                });
            }

            if (addLists.size() != 0) {
                groupListBatchDao.insertAndUpdateBatch(addLists);
            }

            if (delLists.size() != 0) {
                GroupListExample example = new GroupListExample();
                example.or().andBotIdEqualTo(botId).andGroupIdIn(delLists);
                groupListDao.deleteByExample(example);
            }
        }
    }

    private GroupList groupTransfer(String botId, GroupModel.Group group) {
        return new GroupList()
                .setGroupName(group.getGroup_name())
                .setMaxMemberCount(group.getMax_member_count())
                .setMemberCount(group.getMember_count())
                .setBotId(botId)
                .setGroupId(group.getGroup_id());
    }

}
