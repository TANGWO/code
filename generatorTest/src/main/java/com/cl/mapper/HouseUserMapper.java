package com.cl.mapper;

import com.cl.entity.HouseUser;
import com.cl.entity.HouseUserExample;
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

public interface HouseUserMapper {
    @SelectProvider(type=HouseUserSqlProvider.class, method="countByExample")
    int countByExample(HouseUserExample example);

    @DeleteProvider(type=HouseUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(HouseUserExample example);

    @Delete({
        "delete from house_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into house_user (id, house_id, ",
        "user_id, create_time, ",
        "type)",
        "values (#{id,jdbcType=BIGINT}, #{houseId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{createTime,jdbcType=DATE}, ",
        "#{type,jdbcType=BIT})"
    })
    int insert(HouseUser record);

    @InsertProvider(type=HouseUserSqlProvider.class, method="insertSelective")
    int insertSelective(HouseUser record);

    @SelectProvider(type=HouseUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="house_id", property="houseId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="type", property="type", jdbcType=JdbcType.BIT)
    })
    List<HouseUser> selectByExample(HouseUserExample example);

    @Select({
        "select",
        "id, house_id, user_id, create_time, type",
        "from house_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="house_id", property="houseId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="type", property="type", jdbcType=JdbcType.BIT)
    })
    HouseUser selectByPrimaryKey(Long id);

    @UpdateProvider(type=HouseUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") HouseUser record, @Param("example") HouseUserExample example);

    @UpdateProvider(type=HouseUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") HouseUser record, @Param("example") HouseUserExample example);

    @UpdateProvider(type=HouseUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HouseUser record);

    @Update({
        "update house_user",
        "set house_id = #{houseId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=DATE},",
          "type = #{type,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(HouseUser record);
}