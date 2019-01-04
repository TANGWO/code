package com.cl.mapper;

import com.cl.entity.Blog;
import com.cl.entity.BlogExample;
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

public interface BlogMapper {
    @SelectProvider(type=BlogSqlProvider.class, method="countByExample")
    int countByExample(BlogExample example);

    @DeleteProvider(type=BlogSqlProvider.class, method="deleteByExample")
    int deleteByExample(BlogExample example);

    @Delete({
        "delete from blog",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into blog (id, tags, ",
        "create_time, title, ",
        "cat, content)",
        "values (#{id,jdbcType=INTEGER}, #{tags,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=DATE}, #{title,jdbcType=VARCHAR}, ",
        "#{cat,jdbcType=INTEGER}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Blog record);

    @InsertProvider(type=BlogSqlProvider.class, method="insertSelective")
    int insertSelective(Blog record);

    @SelectProvider(type=BlogSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="cat", property="cat", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    @SelectProvider(type=BlogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="cat", property="cat", jdbcType=JdbcType.INTEGER)
    })
    List<Blog> selectByExample(BlogExample example);

    @Select({
        "select",
        "id, tags, create_time, title, cat, content",
        "from blog",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="cat", property="cat", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Blog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Blog record);

    @Update({
        "update blog",
        "set tags = #{tags,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=DATE},",
          "title = #{title,jdbcType=VARCHAR},",
          "cat = #{cat,jdbcType=INTEGER},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Blog record);

    @Update({
        "update blog",
        "set tags = #{tags,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=DATE},",
          "title = #{title,jdbcType=VARCHAR},",
          "cat = #{cat,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Blog record);
}