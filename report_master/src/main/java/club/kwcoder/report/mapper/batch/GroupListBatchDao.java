package club.kwcoder.report.mapper.batch;

import club.kwcoder.report.dataobject.GroupList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupListBatchDao {

    void insertAndUpdateBatch(@Param("groupList") List<GroupList> groupLists);

}
