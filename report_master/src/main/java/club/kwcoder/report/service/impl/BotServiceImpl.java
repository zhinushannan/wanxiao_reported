package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.BotExample;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private BotDao botDao;

    @Override
    public ResultBean<List<Bot>> list() {
        List<Bot> bots = botDao.selectByExample(new BotExample());
        return ResultBean.ok("Bots查询成功！", bots);
    }
}
