package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.Account;
import club.kwcoder.report.dataobject.Clazz;
import club.kwcoder.report.dataobject.Student;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;
import club.kwcoder.report.model.dto.TeacherDTO;
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

    @RequestMapping(value = "class/list", method = RequestMethod.POST)
    public ResultBean<PageBean<Clazz>> clazzList(@RequestBody PageBean<Clazz> pageBean) {
        return dataManagerService.clazzList(pageBean);
    }

    @RequestMapping(value = "class/modify", method = RequestMethod.POST)
    public ResultBean<String> clazzModify(@RequestBody DataInsertDTO dataInsert) {
        return dataManagerService.modifyClazz(dataInsert);
    }

    @RequestMapping(value = "class/delete", method = RequestMethod.GET)
    public ResultBean<String> clazzDelete(@RequestParam("class") String clazzName) {
        return dataManagerService.deleteClazz(clazzName);
    }

    @RequestMapping(value = "student/list", method = RequestMethod.GET)
    public ResultBean<List<Student>> studentList(@RequestParam(name = "class") String studentClazz) {
        return dataManagerService.studentList(studentClazz);
    }

    @RequestMapping(value = "student/modify", method = RequestMethod.POST)
    public ResultBean<String> studentModify(@RequestBody Student student) {
        return dataManagerService.studentModify(student);
    }

    @RequestMapping(value = "account/save", method = RequestMethod.POST)
    public ResultBean<String> accountSave(@RequestBody Account account) {
        System.out.println(account);
        return dataManagerService.accountSave(account);
    }

    @RequestMapping(value = "account/list", method = RequestMethod.POST)
    public ResultBean<PageBean<TeacherDTO>> accountList(@RequestBody PageBean<TeacherDTO> pageBean) {
        return dataManagerService.accountList(pageBean);
    }

    @RequestMapping(value = "account/delete", method = RequestMethod.GET)
    public ResultBean<String> accountDelete(@RequestParam(name = "teacherName") String teacherName) {
        return dataManagerService.accountDelete(teacherName);
    }

}
