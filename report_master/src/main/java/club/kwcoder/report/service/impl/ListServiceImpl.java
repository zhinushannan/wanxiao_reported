package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.FriendList;
import club.kwcoder.report.dataobject.FriendListExample;
import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.dataobject.GroupListExample;
import club.kwcoder.report.mapper.FriendListDao;
import club.kwcoder.report.mapper.GroupListDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.ListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private FriendListDao friendListDao;

    @Autowired
    private GroupListDao groupListDao;

    @Override
    public ResultBean<PageBean<FriendList>> friendList(PageBean<FriendList> pageBean, String botId) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());

        FriendListExample friendListExample = new FriendListExample();
        friendListExample.or().andBotIdEqualTo(botId);
        List<FriendList> friendLists = friendListDao.selectByExample(friendListExample);

        PageInfo<FriendList> friendListPageInfo = new PageInfo<>(friendLists);

        pageBean
                .setTotal(friendListPageInfo.getTotal())
                .setData(friendLists);

        return ResultBean.ok("查询成功！", pageBean);
    }

    @Override
    public ResultBean<PageBean<GroupList>> groupList(PageBean<GroupList> pageBean, String botId) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());

        GroupListExample groupListExample = new GroupListExample();
        groupListExample.or().andBotIdEqualTo(botId);
        List<GroupList> groupLists = groupListDao.selectByExample(groupListExample);

        PageInfo<GroupList> friendListPageInfo = new PageInfo<>(groupLists);

        pageBean
                .setTotal(friendListPageInfo.getTotal())
                .setData(groupLists);

        return ResultBean.ok("查询成功！", pageBean);
    }
}
