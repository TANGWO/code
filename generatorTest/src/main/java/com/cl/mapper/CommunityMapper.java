package com.cl.mapper;

import com.cl.entity.Community;
import com.cl.entity.CommunityExample;
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

public interface CommunityMapper {
    @SelectProvider(type=CommunitySqlProvider.class, method="countByExample")
    int countByExample(CommunityExample example);

    @DeleteProvider(type=CommunitySqlProvider.class, method="deleteByExample")
    int deleteByExample(CommunityExample example);

    @Delete({
        "delete from community",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into community (id, city_code, ",
        "name, city_name)",
        "values (#{id,jdbcType=INTEGER}, #{cityCode,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{cityName,jdbcType=VARCHAR})"
    })
    int insert(Community record);

    @InsertProvider(type=CommunitySqlProvider.class, method="insertSelective")
    int insertSelective(Community record);

    @SelectProvider(type=CommunitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="city_code", property="cityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="city_name", property="cityName", jdbcType=JdbcType.VARCHAR)
    })
    List<Community> selectByExample(CommunityExample example);

    @Select({
        "select",
        "id, city_code, name, city_name",
        "from community",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="city_code", property="cityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="city_name", property="cityName", jdbcType=JdbcType.VARCHAR)
    })
    Community selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CommunitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Community record, @Param("example") CommunityExample example);

    @UpdateProvider(type=CommunitySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Community record, @Param("example") CommunityExample example);

    @UpdateProvider(type=CommunitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Community record);

    @Update({
        "update community",
        "set city_code = #{cityCode,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "city_name = #{cityName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Community record);
}