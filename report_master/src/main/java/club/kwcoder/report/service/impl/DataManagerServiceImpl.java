package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.*;
import club.kwcoder.report.mapper.AccountDao;
import club.kwcoder.report.mapper.ClazzDao;
import club.kwcoder.report.mapper.ReportTimeDao;
import club.kwcoder.report.mapper.StudentDao;
import club.kwcoder.report.mapper.batch.AccountCustomDao;
import club.kwcoder.report.mapper.batch.ClazzCustomDao;
import club.kwcoder.report.mapper.batch.StudentBatchDao;
import club.kwcoder.report.model.bean.PageBean;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.model.dto.DataInsertDTO;
import club.kwcoder.report.model.dto.TeacherDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataManagerServiceImpl implements DataManagerService {

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private ClazzCustomDao clazzCustomDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentBatchDao studentBatchDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountCustomDao accountCustomDao;

    @Autowired
    private ReportTimeDao reportTimeDao;

    @Override
    @Transactional
    public ResultBean<String> insertData(DataInsertDTO dataInsert) {
        if (clazzDao.selectByPrimaryKey(dataInsert.getClazzName()) != null) {
            return ResultBean.error("该班级已在系统内，不能重复添加！", null);
        }

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Clazz clazz = new Clazz(dataInsert.getClazzName(), dataInsert.getTeacherName(), String.format("%s.%s.%s", tomorrow.getYear(), tomorrow.getMonthValue(), tomorrow.getDayOfMonth()), dataInsert.getDeptId(), dataInsert.getGroupId(), dataInsert.getBotPort(), dataInsert.getDelete());

        CsvImportParams importParams = new CsvImportParams();
        importParams.setTitleRows(0);
        List<Student> students;
        try {
            students = CsvImportUtil.importCsv(new FileInputStream("/home/zhinushannan/Desktop/data-insert.csv"), Student.class, importParams);
            System.out.println(students);
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
                .setBotPort(dataInsert.getBotPort())
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

    @Override
    public ResultBean<String> studentModify(Student student) {
        studentDao.updateByPrimaryKeySelective(student);
        return ResultBean.ok("修改成功！", null);
    }

    @Override
    public ResultBean<PageBean<TeacherDTO>> accountList(PageBean<TeacherDTO> pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());

        List<Account> accounts = accountDao.selectByExample(new AccountExample());

        List<TeacherDTO> list = new ArrayList<>();
        accounts.forEach(account -> {
            ClazzExample example = new ClazzExample();
            example.createCriteria().andTeacherNameEqualTo(account.getTeacherName());
            List<String> clazz = new ArrayList<>();
            clazzDao.selectByExample(example).forEach(e -> clazz.add(e.getClazzName()));

            list.add(new TeacherDTO()
                    .setTeacherName(account.getTeacherName())
                    .setUsername(account.getUsername())
                    .setPassword(account.getPassword())
                    .setClazz(clazz));
        });

        PageInfo<Account> of = PageInfo.of(accounts);

        pageBean
                .setTotal(of.getTotal())
                .setData(list);
        return ResultBean.ok("查询成功！", pageBean);
    }

    @Override
    public ResultBean<String> accountSave(Account account) {
        accountCustomDao.insertAndUpdate(account);
        return ResultBean.ok("操作成功！", null);
    }

    @Override
    @Transactional
    public ResultBean<String> accountDelete(String teacherName) {
        int i = accountDao.deleteByPrimaryKey(teacherName);
        if (i != 1) {
            return ResultBean.error("删除失败，请稍后重试！", null);
        }

        ClazzExample example = new ClazzExample();
        example.createCriteria().andTeacherNameEqualTo(teacherName);
        List<Clazz> collect = clazzDao.selectByExample(example).stream().filter(clazz -> {
            clazz.setTeacherName(null);
            return true;
        }).collect(Collectors.toList());

        if (collect.size() != 0) {
            clazzCustomDao.insertAndUpdate(collect);
        }

        return ResultBean.ok("删除成功，请注意设置相应班级的新辅导员！", null);
    }

    @Override
    public ResultBean<List<Integer>> reportList(String clazzName) {
        ReportTimeExample reportTimeExample = new ReportTimeExample();
        reportTimeExample.createCriteria().andClazzNameEqualTo(clazzName);

        List<Integer> times = new ArrayList<>();

        reportTimeDao.selectByExample(reportTimeExample).forEach(reportTime -> times.add(reportTime.getTime()));

        return ResultBean.ok("查询成功！", times);
    }
}
