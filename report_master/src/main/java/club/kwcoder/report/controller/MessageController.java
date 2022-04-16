package club.kwcoder.report.controller;

import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.MessageDTO;
import club.kwcoder.report.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/send/private", method = RequestMethod.POST)
    public ResultBean<String> sendPrivateMessage(@RequestBody MessageDTO message) {
        return messageService.sendPrivateMessage(message);
    }

    @RequestMapping(value = "/send/group", method = RequestMethod.POST)
    public ResultBean<String> sendGroupMessage(@RequestBody MessageDTO message) {
        return messageService.sendGroupMessage(message);
    }

}
