package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.BotDTO;
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

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultBean<List<Bot>> list() {
        return botService.list();
    }

    @RequestMapping(value = "add/port", method = RequestMethod.GET)
    public ResultBean<Integer> getAvailablePort(@RequestParam(name = "port", required = false) Integer port) {
        return botService.getAvailablePort(port);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultBean<String> add(@RequestBody BotDTO bot) {
        return botService.add(bot);
    }

    @RequestMapping(value = "add/qrcode", method = RequestMethod.GET)
    public ResultBean<String> qrcode(@RequestParam(name = "port") String port) {
        return botService.qrcode(port);
    }

    @RequestMapping(value = "log", method = RequestMethod.GET)
    public ResultBean<List<LogDTO>> logs(@RequestParam(name = "botId") String botId,
                                         @RequestParam(name = "sessionId", required = false) String sessionId) {
        return botService.logs(botId, sessionId);
    }


}
