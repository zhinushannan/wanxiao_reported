package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Clazz;
import club.kwcoder.report.dataobject.ClazzExample;
import club.kwcoder.report.dataobject.Student;
import club.kwcoder.report.dataobject.StudentExample;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.mapper.StudentDao;
import club.kwcoder.report.mapper.batch.StudentBatchDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;
import club.kwcoder.report.service.DataManagerService;
import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DataManagerServiceImpl implements DataManagerService {

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentBatchDao studentBatchDao;

    @Override
    @Transactional
    public ResultBean<String> insertData(DataInsertDTO dataInsert) {
        if (clazzDao.selectByPrimaryKey(dataInsert.getClazzName()) != null) {
            return ResultBean.error("该班级已在系统内，不能重复添加！", null);
        }

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Clazz clazz = new Clazz(dataInsert.getClazzName(), dataInsert.getTeacherName(), String.format("%s.%s.%s", tomorrow.getYear(), tomorrow.getMonthValue(), tomorrow.getDayOfMonth()), dataInsert.getDeptId(), dataInsert.getGroupId(), dataInsert.getBotId(), dataInsert.getDelete());

        CsvImportParams importParams = new CsvImportParams();
        importParams.setTitleRows(0);
        List<Student> students;
        try {
            students = CsvImportUtil.importCsv(new FileInputStream("/home/zhinushannan/Desktop/data-insert.csv"), Student.class, importParams);
            students.forEach(s -> s.setStudentClazz(dataInsert.getClazzName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResultBean.error("系统异常，请稍后重试！", null);
        }

        clazzDao.insert(clazz);
        studentBatchDao.insertAndUpdateBatch(students);

        return ResultBean.ok("新增成功！", null);
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
        studentExample.setOrderByClause("remove, CONVERT(student_name USING gbk) COLLATE gbk_chinese_ci");
        List<Student> students = studentDao.selectByExample(studentExample);
        return ResultBean.ok("查询成功！", students);
    }

    @Override
    public ResultBean<String> modifyClazz(DataInsertDTO dataInsert) {
        Clazz clazz = new Clazz()
                .setClazzName(dataInsert.getClazzName())
                .setTeacherName(dataInsert.getTeacherName())
                .setDeptId(dataInsert.getDeptId())
                .setGroupId(dataInsert.getGroupId())
                .setBotId(dataInsert.getBotId())
                .setDelete(dataInsert.getDelete());
        clazzDao.updateByPrimaryKeySelective(clazz);
        return ResultBean.ok("修改成功！", null);
    }

    @Override
    @Transactional
    public ResultBean<String> deleteClazz(String clazzName) {
        int i = clazzDao.deleteByPrimaryKey(clazzName);

        StudentExample example = new StudentExample();
        example.createCriteria().andStudentClazzEqualTo(clazzName);
        studentDao.deleteByExample(example);

        return i == 1 ? ResultBean.ok("删除成功！", null) : ResultBean.error("删除失败！班级不存在或系统错误！", null);
    }
}
