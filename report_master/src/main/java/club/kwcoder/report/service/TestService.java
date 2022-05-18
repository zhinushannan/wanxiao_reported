package club.kwcoder.report.service;

import club.kwcoder.report.model.bean.ResultBean;

import java.util.List;

public interface TestService {

    ResultBean<List<String>> clazzList();

}
