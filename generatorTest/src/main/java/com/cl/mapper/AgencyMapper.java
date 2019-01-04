package com.cl.mapper;

import com.cl.entity.Agency;
import com.cl.entity.AgencyExample;
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

public interface AgencyMapper {
    @SelectProvider(type=AgencySqlProvider.class, method="countByExample")
    int countByExample(AgencyExample example);

    @DeleteProvider(type=AgencySqlProvider.class, method="deleteByExample")
    int deleteByExample(AgencyExample example);

    @Delete({
        "delete from agency",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into agency (id, name, ",
        "address, phone, ",
        "email, about_us, ",
        "mobile, web_site)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{aboutUs,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{webSite,jdbcType=VARCHAR})"
    })
    int insert(Agency record);

    @InsertProvider(type=AgencySqlProvider.class, method="insertSelective")
    int insertSelective(Agency record);

    @SelectProvider(type=AgencySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="about_us", property="aboutUs", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="web_site", property="webSite", jdbcType=JdbcType.VARCHAR)
    })
    List<Agency> selectByExample(AgencyExample example);

    @Select({
        "select",
        "id, name, address, phone, email, about_us, mobile, web_site",
        "from agency",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="about_us", property="aboutUs", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="web_site", property="webSite", jdbcType=JdbcType.VARCHAR)
    })
    Agency selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AgencySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Agency record, @Param("example") AgencyExample example);

    @UpdateProvider(type=AgencySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Agency record, @Param("example") AgencyExample example);

    @UpdateProvider(type=AgencySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Agency record);

    @Update({
        "update agency",
        "set name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "about_us = #{aboutUs,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "web_site = #{webSite,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Agency record);
}