package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.BotRequestExample;
import club.kwcoder.report.mapper.BotDao;
import club.kwcoder.report.mapper.BotRequestDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.dataobject.BotRequest;
import club.kwcoder.report.model.dto.FriendRequestDTO;
import club.kwcoder.report.service.ListFlush;
import club.kwcoder.report.service.RequestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private BotRequestDao botRequestDao;

    @Autowired
    private BotDao botDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ListFlush listFlush;

    @Override
    public ResultBean<PageBean<BotRequest>> list(PageBean<BotRequest> pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<BotRequest> botRequests = botRequestDao.selectByExample(new BotRequestExample());
        PageInfo<BotRequest> pageInfo = new PageInfo<>(botRequests);
        pageBean
                .setTotal(pageInfo.getTotal())
                .setData(botRequests);
        return ResultBean.ok("查询成功！", pageBean);
    }

}
