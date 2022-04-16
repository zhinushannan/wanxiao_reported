package club.kwcoder.report.service.impl;

import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.MessageDTO;
import club.kwcoder.report.mapper.MessageDao;
import club.kwcoder.report.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public ResultBean<String> sendPrivateMessage(MessageDTO message) {
        System.out.println(message);
        return null;
    }

    @Override
    public ResultBean<String> sendGroupMessage(MessageDTO message) {
        System.out.println(message);
        return null;
    }
}
