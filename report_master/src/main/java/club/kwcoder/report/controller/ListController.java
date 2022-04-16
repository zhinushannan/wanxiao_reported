package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.FriendList;
import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/list")
public class ListController {

    @Autowired
    private ListService listService;

    @RequestMapping(value = "/friend", method = RequestMethod.GET)
    public ResultBean<PageBean<FriendList>> friendList(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                       @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                                                       @RequestParam(name = "botId", required = true) String botId) {
        PageBean<FriendList> pageBean = new PageBean<FriendList>()
                .setPage(page)
                .setSize(size);
        return listService.friendList(pageBean, botId);
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResultBean<PageBean<GroupList>> groupList(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                      @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                                                      @RequestParam(name = "botId", required = true) String botId) {
        PageBean<GroupList> pageBean = new PageBean<GroupList>()
                .setPage(page)
                .setSize(size);
        return listService.groupList(pageBean, botId);
    }


}
