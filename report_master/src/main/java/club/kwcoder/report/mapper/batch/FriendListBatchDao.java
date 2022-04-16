package club.kwcoder.report.mapper.batch;

import club.kwcoder.report.dataobject.FriendList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendListBatchDao {

    void insertAndUpdateBatch(@Param("friendList") List<FriendList> friendLists);

}
