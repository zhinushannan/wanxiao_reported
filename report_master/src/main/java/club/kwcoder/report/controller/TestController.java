package club.kwcoder.report.controller;

import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.TestDTO;
import club.kwcoder.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "clazzList", method = RequestMethod.GET)
    public ResultBean<List<String>> clazzList() {
        return testService.clazzList();
    }

    @RequestMapping(value = "do", method = RequestMethod.POST)
    public ResultBean<Map<String, String>> doTest(@RequestBody TestDTO test) {
        return testService.doTest(test);
    }

}
