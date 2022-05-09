package club.kwcoder.report.mapper.batch;

import club.kwcoder.report.dataobject.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzCustomDao {
    void insertAndUpdate(@Param("clazzList") List<Clazz> clazzList);
}
