package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.BotRequestExample;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.mapper.BotRequestDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.dataobject.BotRequest;
import club.kwcoder.report.model.dto.FriendRequestDTO;
import club.kwcoder.report.service.ListFlush;
import club.kwcoder.report.service.RequestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private BotRequestDao botRequestDao;

    @Autowired
    private BotDao botDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ListFlush listFlush;

    @Override
    public ResultBean<PageBean<BotRequest>> list(PageBean<BotRequest> pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<BotRequest> botRequests = botRequestDao.selectByExample(new BotRequestExample());
        PageInfo<BotRequest> pageInfo = new PageInfo<>(botRequests);
        pageBean
                .setTotal(pageInfo.getTotal())
                .setData(botRequests);
        return ResultBean.ok("查询成功！", pageBean);
    }

    @Override
    public ResultBean<String> friendRequest(FriendRequestDTO friendRequest) {
        Bot bot = botDao.selectByPrimaryKey(friendRequest.getBotId());

        String url = "http://localhost:" + bot.getPort() + "/set_friend_add_request?flag=" + friendRequest.getFlag() + "&approve=" + friendRequest.getApprove() + "&remark=" + friendRequest.getMark();

        ResponseEntity<Object> forEntity = restTemplate.getForEntity(url, Object.class);

        if (forEntity.hasBody()) {
            BotRequestExample example = new BotRequestExample();
            example.or().andFlagEqualTo(friendRequest.getFlag());
            List<BotRequest> botRequests = botRequestDao.selectByExample(example);

            example.clear();
            example.or().andBotIdEqualTo(bot.getBotId()).andTargetIdEqualTo(botRequests.get(0).getTargetId());
            botRequestDao.deleteByExample(example);
        }

        if (friendRequest.getApprove()) {
            listFlush.friendFlushList(bot.getBotId(), bot.getPort());
            return ResultBean.ok("已同意添加该好友！", "已同意添加该好友！");
        }

        return ResultBean.ok("已拒绝添加该好友！", "已拒绝添加该好友！");
    }
}
