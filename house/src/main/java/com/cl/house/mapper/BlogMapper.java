 package com.cl.house.mapper;

import java.util.List;

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
	Long selectBlogCount(Blog query);

	/**
	 * @param query
	 * @param params
	 * @return
	 */
	List<Blog> selectBlog(Blog query, PageParams params);

}
