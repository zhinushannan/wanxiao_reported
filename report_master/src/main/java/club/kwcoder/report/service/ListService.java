package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;

public interface ListService {

    ResultBean<PageBean<GroupList>> groupList(PageBean<GroupList> pageBean, String botId);

}
