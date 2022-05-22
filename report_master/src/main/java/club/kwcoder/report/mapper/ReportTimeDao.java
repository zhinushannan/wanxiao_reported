package club.kwcoder.report.mapper;

import club.kwcoder.report.dataobject.ReportTime;
import club.kwcoder.report.dataobject.ReportTimeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportTimeDao {
    long countByExample(ReportTimeExample example);

    int deleteByExample(ReportTimeExample example);

    int insert(ReportTime record);

    int insertSelective(ReportTime record);

    List<ReportTime> selectByExample(ReportTimeExample example);

    int updateByExampleSelective(@Param("record") ReportTime record, @Param("example") ReportTimeExample example);

    int updateByExample(@Param("record") ReportTime record, @Param("example") ReportTimeExample example);
}