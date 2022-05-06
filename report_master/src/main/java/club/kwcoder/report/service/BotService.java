package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.model.bean.ResultBean;

import java.util.List;

public interface BotService {
    ResultBean<List<Bot>> list();
}
