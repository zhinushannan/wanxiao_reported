package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.Clazz;
import club.kwcoder.report.dataobject.Student;
import club.kwcoder.report.dataobject.StudentExample;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;
import club.kwcoder.report.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/")
public class DataManagerController {

    @Autowired
    private DataManagerService dataManagerService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultBean<String> insertData(@RequestBody DataInsertDTO dataInsert) {
        return dataManagerService.insertData(dataInsert);
    }

    @RequestMapping(value = "list/class", method = RequestMethod.POST)
    public ResultBean<PageBean<Clazz>> clazzList(@RequestBody PageBean<Clazz> pageBean) {
        return dataManagerService.clazzList(pageBean);
    }

    @RequestMapping(value = "modify/class", method = RequestMethod.POST)
    public ResultBean<String> clazzModify(@RequestBody DataInsertDTO dataInsert) {
        return dataManagerService.modifyClazz(dataInsert);
    }

    @RequestMapping(value = "delete/class", method = RequestMethod.GET)
    public ResultBean<String> clazzDelete(@RequestParam("class") String clazzName) {
        return dataManagerService.deleteClazz(clazzName);
    }

    @RequestMapping(value = "list/student", method = RequestMethod.GET)
    public ResultBean<List<Student>> studentList(@RequestParam(name = "class") String studentClazz) {
        return dataManagerService.studentList(studentClazz);
    }

    @RequestMapping(value = "/modify/stu", method = RequestMethod.POST)
    public ResultBean<String> studentModify(@RequestBody Student student) {
        return dataManagerService.studentModify(student);
    }


}
