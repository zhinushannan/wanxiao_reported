package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.ClazzExample;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.TestDTO;
import club.kwcoder.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultBean<List<String>> clazzList() {
        List<String> clazz = new ArrayList<>();

        clazzDao.selectByExample(new ClazzExample()).forEach(c -> clazz.add(c.getClazzName()));

        return ResultBean.ok("查询成功！", clazz);
    }

    @Override
    public ResultBean<Map<String, String>> doTest(TestDTO test) {
        String url = String.format("http://127.0.0.1:5000/appoint_clazz?clazz_name=%s&report_type=%s&bot_id=%s", test.getClazz(), test.getType(), test.getBotId());
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);

        Map<String, String> body;
        if (forEntity.getBody() != null) {
            body = forEntity.getBody();
            return ResultBean.ok("测试成功！", body);
        } else {
            return ResultBean.error("测试失败，请稍后重试！", null);
        }
    }
}
