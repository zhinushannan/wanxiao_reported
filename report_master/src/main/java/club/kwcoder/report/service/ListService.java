package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.FriendList;
import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;

public interface ListService {

    ResultBean<PageBean<FriendList>> friendList(PageBean<FriendList> pageBean, String botId);

    ResultBean<PageBean<GroupList>> groupList(PageBean<GroupList> pageBean, String botId);

}
