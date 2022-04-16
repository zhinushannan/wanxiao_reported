package club.kwcoder.report.service;

import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.MessageDTO;

public interface MessageService {

    ResultBean<String> sendPrivateMessage(MessageDTO message);

    ResultBean<String> sendGroupMessage(MessageDTO message);

}
