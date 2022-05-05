package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Clazz;
import club.kwcoder.report.dataobject.ClazzExample;
import club.kwcoder.report.dataobject.Student;
import club.kwcoder.report.dataobject.StudentExample;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.mapper.StudentDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;
import club.kwcoder.report.service.DataManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataManagerServiceImpl implements DataManagerService {

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public ResultBean<String> insertData(DataInsertDTO dataInsert) {
        return null;
    }

    @Override
    public ResultBean<PageBean<Clazz>> clazzList(PageBean<Clazz> pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<Clazz> clazzes = clazzDao.selectByExample(new ClazzExample());
        PageInfo<Clazz> of = PageInfo.of(clazzes);
        pageBean
                .setTotal(of.getTotal())
                .setData(clazzes);
        return ResultBean.ok("查询成功！", pageBean);
    }

    @Override
    public ResultBean<List<Student>> studentList(String studentClazz) {
        StudentExample studentExample = new StudentExample();
        studentExample.or().andStudentClazzEqualTo(studentClazz);
        List<Student> students = studentDao.selectByExample(studentExample);
        return ResultBean.ok("查询成功！", students);
    }
}
