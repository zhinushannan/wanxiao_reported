package club.kwcoder.report.task;

import club.kwcoder.report.dataobject.BotExample;
import club.kwcoder.report.dataobject.FriendList;
import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.mapper.batch.FriendListBatchDao;
import club.kwcoder.report.mapper.batch.GroupListBatchDao;
import club.kwcoder.report.model.task.FriendModel;
import club.kwcoder.report.model.task.GroupModel;
import club.kwcoder.report.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务：
 * 刷新机器人的群组、好友列表，每十分钟一次
 */
@Component
public class FLushListTask {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GroupListBatchDao groupListBatchDao;

    @Autowired
    private FriendListBatchDao friendListBatchDao;

    @Autowired
    private BotDao botDao;

    /**
     * 先从数据库中获取机器人列表，获取到机器人的qq号(botId)和端口号
     */
    @Scheduled(fixedRate = 600000)
    public void friendListFlushTask() {
        BotExample botExample = new BotExample();
        botDao.selectByExample(botExample).forEach(bot -> {
            groupFlushList(bot.getBotId(), bot.getPort());
            friendFlushList(bot.getBotId(), bot.getPort());
        });
    }

    private void groupFlushList(String botId, int port) {
        String url = "http://localhost:" + port + "/get_group_list";
        ResponseEntity<GroupModel> groupList = restTemplate.getForEntity(url, GroupModel.class);
        if (groupList.getBody() != null) {
            GroupModel body = groupList.getBody();
            List<GroupModel.Group> data = body.getData();

            String baseKey = botId + ":group:";
            List<GroupList> groupLists = new ArrayList<>();

            data.forEach(group -> {
                String groupFromRedis = redisUtil.getString(baseKey + group.getGroup_id());
                if (!StringUtils.equals(groupFromRedis, group.toString())) {
                    redisUtil.setString(baseKey + group.getGroup_id(), group.toString());
                    groupLists.add(
                            new GroupList()
                                    .setGroupName(group.getGroup_name())
                                    .setMaxMemberCount(group.getMax_member_count())
                                    .setMemberCount(group.getMember_count())
                                    .setBotId(botId)
                                    .setGroupId(group.getGroup_id())
                    );
                }
            });
            if (groupLists.size() != 0) {
                groupListBatchDao.insertAndUpdateBatch(groupLists);
            }
        }
    }

    private void friendFlushList(String botId, int port) {
        String url = "http://localhost:" + port + "/get_friend_list";
        ResponseEntity<FriendModel> friendList = restTemplate.getForEntity(url, FriendModel.class);
        if (friendList.getBody() != null) {
            FriendModel body = friendList.getBody();
            List<FriendModel.Friend> data = body.getData();

            String baseKey = botId + ":friend:";
            List<FriendList> friendLists = new ArrayList<>();

            data.forEach(friend -> {
                String groupFromRedis = redisUtil.getString(baseKey + friend.getUser_id());
                if (!StringUtils.equals(groupFromRedis, friend.toString())) {
                    redisUtil.setString(baseKey + friend.getUser_id(), friend.toString());
                    friendLists.add(
                            new FriendList()
                                    .setNickname(friend.getNickname())
                                    .setNickname(friend.getUser_id())
                                    .setUserId(friend.getUser_id())
                    );
                }
            });
            if (friendLists.size() != 0) {
                friendListBatchDao.insertAndUpdateBatch(friendLists);
            }
        }
    }

}
