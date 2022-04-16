package club.kwcoder.report.mapper;

import club.kwcoder.report.dataobject.FriendList;
import club.kwcoder.report.dataobject.FriendListExample;
import club.kwcoder.report.dataobject.FriendListKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendListDao {
    long countByExample(FriendListExample example);

    int deleteByExample(FriendListExample example);

    int deleteByPrimaryKey(FriendListKey key);

    int insert(FriendList record);

    int insertSelective(FriendList record);

    List<FriendList> selectByExample(FriendListExample example);

    FriendList selectByPrimaryKey(FriendListKey key);

    int updateByExampleSelective(@Param("record") FriendList record, @Param("example") FriendListExample example);

    int updateByExample(@Param("record") FriendList record, @Param("example") FriendListExample example);

    int updateByPrimaryKeySelective(FriendList record);

    int updateByPrimaryKey(FriendList record);
}