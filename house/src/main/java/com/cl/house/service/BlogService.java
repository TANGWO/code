 package com.cl.house.service;

import com.cl.house.common.model.Blog;
import com.cl.house.common.page.PageData;
import com.cl.house.common.page.PageParams;

/**
 * @author mi
 * @date 2018/12/17
 */
public interface BlogService {

	/**
	 * @param id
	 * @return
	 */
	Blog queryOneBlog(int id);

	/**
	 * @param query
	 * @param build
	 * @return
	 */
	PageData<Blog> queryBlog(Blog query, PageParams build);

}
