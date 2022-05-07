package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.Clazz;
import club.kwcoder.report.dataobject.Student;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;

import java.util.List;

public interface DataManagerService {
    ResultBean<String> insertData(DataInsertDTO dataInsert);

    ResultBean<PageBean<Clazz>> clazzList(PageBean<Clazz> pageBean);

    ResultBean<List<Student>> studentList(String studentClazz);

    ResultBean<String> modifyClazz(DataInsertDTO dataInsert);

    ResultBean<String> deleteClazz(String clazzName);

    ResultBean<String> studentModify(Student student);
}
