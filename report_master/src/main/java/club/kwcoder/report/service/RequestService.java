package club.kwcoder.report.service;

import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.dataobject.BotRequest;

public interface RequestService {

    /**
     * 分页获取请求列表
     * @param pageBean 分页的信息
     * @return 返回 BotRequest 对象列表
     */
    ResultBean<PageBean<BotRequest>> list(PageBean<BotRequest> pageBean);

}
