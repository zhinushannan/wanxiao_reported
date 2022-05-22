package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.BotDTO;
import club.kwcoder.report.model.dto.BotInsertDTO;
import club.kwcoder.report.model.dto.GroupDTO;
import club.kwcoder.report.model.dto.LogDTO;

import java.util.List;

public interface BotService {
    ResultBean<PageBean<BotDTO>> list(PageBean<BotDTO> pageBean);

    ResultBean<List<Bot>> list();

    ResultBean<List<LogDTO>> logs(String port, String sessionId);

    ResultBean<Integer> getAvailablePort(Integer port);

    ResultBean<String> add(BotInsertDTO bot);

    ResultBean<String> qrcode(String port);

    ResultBean<List<GroupDTO>> groupList(String botId, String port);

    ResultBean<String> restart(String port);
}
