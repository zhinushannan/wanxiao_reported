package club.kwcoder.report.mapper;

import club.kwcoder.report.dataobject.GroupList;
import club.kwcoder.report.dataobject.GroupListExample;
import club.kwcoder.report.dataobject.GroupListKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GroupListDao {
    long countByExample(GroupListExample example);

    int deleteByExample(GroupListExample example);

    int deleteByPrimaryKey(GroupListKey key);

    int insert(GroupList record);

    int insertSelective(GroupList record);

    List<GroupList> selectByExample(GroupListExample example);

    GroupList selectByPrimaryKey(GroupListKey key);

    int updateByExampleSelective(@Param("record") GroupList record, @Param("example") GroupListExample example);

    int updateByExample(@Param("record") GroupList record, @Param("example") GroupListExample example);

    int updateByPrimaryKeySelective(GroupList record);

    int updateByPrimaryKey(GroupList record);
}