package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.Account;
import club.kwcoder.report.dataobject.Clazz;
import club.kwcoder.report.dataobject.ReportTime;
import club.kwcoder.report.dataobject.Student;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;
import club.kwcoder.report.model.dto.TeacherDTO;

import java.net.InetAddress;
import java.util.List;

public interface DataManagerService {
    ResultBean<String> insertData(DataInsertDTO dataInsert);

    ResultBean<PageBean<Clazz>> clazzList(PageBean<Clazz> pageBean);

    ResultBean<List<Student>> studentList(String studentClazz);

    ResultBean<String> modifyClazz(DataInsertDTO dataInsert);

    ResultBean<String> deleteClazz(String clazzName);

    ResultBean<String> studentModify(Student student);

    ResultBean<PageBean<TeacherDTO>> accountList(PageBean<TeacherDTO> pageBean);

    ResultBean<String> accountSave(Account account);

    ResultBean<String> accountDelete(String teacherName);

    ResultBean<List<Integer>> reportList(String clazzName);

    ResultBean<String> reportModify(String clazzName, Integer time, boolean isOpen);
}
