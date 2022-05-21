package club.kwcoder.report.service;

import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.TestDTO;

import java.util.List;
import java.util.Map;

public interface TestService {

    ResultBean<List<String>> clazzList();

    ResultBean<Map<String, String>> doTest(TestDTO test);
}
