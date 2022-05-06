package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
