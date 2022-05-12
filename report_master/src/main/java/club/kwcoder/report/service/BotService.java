package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.BotDTO;
import club.kwcoder.report.model.dto.LogDTO;

import java.util.List;

public interface BotService {
    ResultBean<List<Bot>> list();

    ResultBean<List<LogDTO>> logs(String port, String sessionId);

    ResultBean<Integer> getAvailablePort(Integer port);

    ResultBean<String> add(BotDTO bot);

    ResultBean<String> qrcode(String port);
}
