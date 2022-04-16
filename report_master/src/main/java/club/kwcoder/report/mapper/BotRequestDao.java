package club.kwcoder.report.mapper;

import club.kwcoder.report.dataobject.BotRequest;
import club.kwcoder.report.dataobject.BotRequestExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BotRequestDao {
    long countByExample(BotRequestExample example);

    int deleteByExample(BotRequestExample example);

    int deleteByPrimaryKey(Integer requestId);

    int insert(BotRequest record);

    int insertSelective(BotRequest record);

    List<BotRequest> selectByExample(BotRequestExample example);

    BotRequest selectByPrimaryKey(Integer requestId);

    int updateByExampleSelective(@Param("record") BotRequest record, @Param("example") BotRequestExample example);

    int updateByExample(@Param("record") BotRequest record, @Param("example") BotRequestExample example);

    int updateByPrimaryKeySelective(BotRequest record);

    int updateByPrimaryKey(BotRequest record);
}