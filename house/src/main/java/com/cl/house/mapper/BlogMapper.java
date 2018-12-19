 package com.cl.house.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cl.house.common.model.Blog;
import com.cl.house.common.page.PageParams;

/**
 * @author chenling
 * @date 2018/12/17
 */
public interface BlogMapper {

	/**
	 * @param query
	 * @return
	 */
	Long selectBlogCount(@Param("blog")Blog blog);

	/**
	 * @param query
	 * @param params
	 * @return
	 */
	List<Blog> selectBlog(@Param("blog")Blog blog, @Param("pageParams")PageParams pageParams);

}
