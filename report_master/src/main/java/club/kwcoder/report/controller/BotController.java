package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.BotDTO;
import club.kwcoder.report.model.dto.BotInsertDTO;
import club.kwcoder.report.model.dto.GroupDTO;
import club.kwcoder.report.model.dto.LogDTO;
import club.kwcoder.report.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bot/")
public class BotController {

    @Autowired
    BotService botService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultBean<PageBean<BotDTO>> list(@RequestBody PageBean<BotDTO> pageBean) {
        return botService.list(pageBean);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultBean<List<Bot>> list() {
        return botService.list();
    }

    @RequestMapping(value = "add/port", method = RequestMethod.GET)
    public ResultBean<Integer> getAvailablePort(@RequestParam(name = "port", required = false) Integer port) {
        return botService.getAvailablePort(port);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultBean<String> add(@RequestBody BotInsertDTO bot) {
        return botService.add(bot);
    }

    @RequestMapping(value = "add/qrcode", method = RequestMethod.GET)
    public ResultBean<String> qrcode(@RequestParam(name = "port") String port) {
        return botService.qrcode(port);
    }

    @RequestMapping(value = "start/log", method = RequestMethod.GET)
    public ResultBean<List<LogDTO>> logs(@RequestParam(name = "port") String port,
                                         @RequestParam(name = "sessionId", required = false) String sessionId) {
        return botService.logs(port, sessionId);
    }

    @RequestMapping(value = "groupList", method = RequestMethod.GET)
    public ResultBean<List<GroupDTO>> groupList(@RequestParam(name = "botId") String botId,
                                                @RequestParam(name = "port") String port) {
        return botService.groupList(botId, port);
    }

    @RequestMapping(value = "restart", method = RequestMethod.GET)
    public ResultBean<String> restart(@RequestParam(name = "port") String port) {
        return botService.restart(port);
    }


}
