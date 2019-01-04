package com.cl.mapper;

import com.cl.entity.House;
import com.cl.entity.HouseExample;
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

public interface HouseMapper {
    @SelectProvider(type=HouseSqlProvider.class, method="countByExample")
    int countByExample(HouseExample example);

    @DeleteProvider(type=HouseSqlProvider.class, method="deleteByExample")
    int deleteByExample(HouseExample example);

    @Delete({
        "delete from house",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into house (id, name, ",
        "type, price, images, ",
        "area, beds, baths, ",
        "rating, remarks, ",
        "properties, floor_plan, ",
        "tags, create_time, ",
        "city_id, community_id, ",
        "address, state)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=BIT}, #{price,jdbcType=INTEGER}, #{images,jdbcType=VARCHAR}, ",
        "#{area,jdbcType=INTEGER}, #{beds,jdbcType=INTEGER}, #{baths,jdbcType=INTEGER}, ",
        "#{rating,jdbcType=DOUBLE}, #{remarks,jdbcType=VARCHAR}, ",
        "#{properties,jdbcType=VARCHAR}, #{floorPlan,jdbcType=VARCHAR}, ",
        "#{tags,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{cityId,jdbcType=INTEGER}, #{communityId,jdbcType=INTEGER}, ",
        "#{address,jdbcType=VARCHAR}, #{state,jdbcType=BIT})"
    })
    int insert(House record);

    @InsertProvider(type=HouseSqlProvider.class, method="insertSelective")
    int insertSelective(House record);

    @SelectProvider(type=HouseSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.BIT),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="images", property="images", jdbcType=JdbcType.VARCHAR),
        @Result(column="area", property="area", jdbcType=JdbcType.INTEGER),
        @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
        @Result(column="baths", property="baths", jdbcType=JdbcType.INTEGER),
        @Result(column="rating", property="rating", jdbcType=JdbcType.DOUBLE),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="floor_plan", property="floorPlan", jdbcType=JdbcType.VARCHAR),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER),
        @Result(column="community_id", property="communityId", jdbcType=JdbcType.INTEGER),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.BIT)
    })
    List<House> selectByExample(HouseExample example);

    @Select({
        "select",
        "id, name, type, price, images, area, beds, baths, rating, remarks, properties, ",
        "floor_plan, tags, create_time, city_id, community_id, address, state",
        "from house",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.BIT),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="images", property="images", jdbcType=JdbcType.VARCHAR),
        @Result(column="area", property="area", jdbcType=JdbcType.INTEGER),
        @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
        @Result(column="baths", property="baths", jdbcType=JdbcType.INTEGER),
        @Result(column="rating", property="rating", jdbcType=JdbcType.DOUBLE),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="floor_plan", property="floorPlan", jdbcType=JdbcType.VARCHAR),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER),
        @Result(column="community_id", property="communityId", jdbcType=JdbcType.INTEGER),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.BIT)
    })
    House selectByPrimaryKey(Long id);

    @UpdateProvider(type=HouseSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    @UpdateProvider(type=HouseSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    @UpdateProvider(type=HouseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(House record);

    @Update({
        "update house",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=BIT},",
          "price = #{price,jdbcType=INTEGER},",
          "images = #{images,jdbcType=VARCHAR},",
          "area = #{area,jdbcType=INTEGER},",
          "beds = #{beds,jdbcType=INTEGER},",
          "baths = #{baths,jdbcType=INTEGER},",
          "rating = #{rating,jdbcType=DOUBLE},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "properties = #{properties,jdbcType=VARCHAR},",
          "floor_plan = #{floorPlan,jdbcType=VARCHAR},",
          "tags = #{tags,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "city_id = #{cityId,jdbcType=INTEGER},",
          "community_id = #{communityId,jdbcType=INTEGER},",
          "address = #{address,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(House record);
}