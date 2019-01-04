package com.cl.mapper;

import com.cl.entity.HouseMsg;
import com.cl.entity.HouseMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HouseMsgMapper {
    @SelectProvider(type=HouseMsgSqlProvider.class, method="countByExample")
    int countByExample(HouseMsgExample example);

    @DeleteProvider(type=HouseMsgSqlProvider.class, method="deleteByExample")
    int deleteByExample(HouseMsgExample example);

    @Delete({
        "delete from house_msg",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into house_msg (id, msg, ",
        "create_time, agent_id, ",
        "house_id, user_name)",
        "values (#{id,jdbcType=BIGINT}, #{msg,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=DATE}, #{agentId,jdbcType=BIGINT}, ",
        "#{houseId,jdbcType=BIGINT}, #{userName,jdbcType=VARCHAR})"
    })
    int insert(HouseMsg record);

    @InsertProvider(type=HouseMsgSqlProvider.class, method="insertSelective")
    int insertSelective(HouseMsg record);

    @SelectProvider(type=HouseMsgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="msg", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="agent_id", property="agentId", jdbcType=JdbcType.BIGINT),
        @Result(column="house_id", property="houseId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    List<HouseMsg> selectByExample(HouseMsgExample example);

    @Select({
        "select",
        "id, msg, create_time, agent_id, house_id, user_name",
        "from house_msg",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="msg", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="agent_id", property="agentId", jdbcType=JdbcType.BIGINT),
        @Result(column="house_id", property="houseId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    HouseMsg selectByPrimaryKey(Long id);

    @UpdateProvider(type=HouseMsgSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") HouseMsg record, @Param("example") HouseMsgExample example);

    @UpdateProvider(type=HouseMsgSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") HouseMsg record, @Param("example") HouseMsgExample example);

    @UpdateProvider(type=HouseMsgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HouseMsg record);

    @Update({
        "update house_msg",
        "set msg = #{msg,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=DATE},",
          "agent_id = #{agentId,jdbcType=BIGINT},",
          "house_id = #{houseId,jdbcType=BIGINT},",
          "user_name = #{userName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(HouseMsg record);
}