package club.kwcoder.report.service;

import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.dataobject.BotRequest;

public interface RequestService {

    ResultBean<PageBean<BotRequest>> list(PageBean<BotRequest> pageBean);

}
