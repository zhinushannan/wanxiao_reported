package club.kwcoder.report.mapper.batch;

import club.kwcoder.report.dataobject.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountCustomDao {

    void insertAndUpdate(@Param("account") Account account);

}
