package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.ClazzExample;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public ResultBean<List<String>> clazzList() {
        List<String> clazz = new ArrayList<>();

        clazzDao.selectByExample(new ClazzExample()).forEach(c -> clazz.add(c.getClazzName()));

        return ResultBean.ok("查询成功！", clazz);
    }
}
