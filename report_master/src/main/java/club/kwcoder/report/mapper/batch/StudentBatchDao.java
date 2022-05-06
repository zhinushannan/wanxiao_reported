package club.kwcoder.report.mapper.batch;

import club.kwcoder.report.dataobject.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentBatchDao {

    void insertAndUpdateBatch(@Param("studentList") List<Student> studentList);

}
