package club.kwcoder.report.controller;

import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.dataobject.BotRequest;
import club.kwcoder.report.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 机器人收到的好友请求和群聊邀请请求，对请求进行处理
 */
@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultBean<PageBean<BotRequest>> list(@RequestBody PageBean<BotRequest> pageBean) {
        return requestService.list(pageBean);
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public ResultBean<String> groupRequest() {
        return null;
    }

}
