package club.kwcoder.report.mapper;

import club.kwcoder.report.dataobject.Bot;
import club.kwcoder.report.dataobject.BotExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BotDao {
    long countByExample(BotExample example);

    int deleteByExample(BotExample example);

    int deleteByPrimaryKey(String botId);

    int insert(Bot record);

    int insertSelective(Bot record);

    List<Bot> selectByExample(BotExample example);

    Bot selectByPrimaryKey(String botId);

    int updateByExampleSelective(@Param("record") Bot record, @Param("example") BotExample example);

    int updateByExample(@Param("record") Bot record, @Param("example") BotExample example);

    int updateByPrimaryKeySelective(Bot record);

    int updateByPrimaryKey(Bot record);
}