 package com.cl.house.service.impl;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.house.common.model.Blog;
import com.cl.house.common.page.PageData;
import com.cl.house.common.page.PageParams;
import com.cl.house.mapper.BlogMapper;
import com.cl.house.service.BlogService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author cehnling
 * @date 2018/12/17
 */
@Service
public class BlogServiceImpl  implements BlogService{
	
	  @Autowired
	  private BlogMapper blogMapper;

	  public PageData<Blog> queryBlog(Blog blog, PageParams pageParams) {
	    List<Blog> blogs =  blogMapper.selectBlog(blog,pageParams);
	    populate(blogs);
	    Long  count =  blogMapper.selectBlogCount(blog);
	    return PageData.<Blog>buildPage(blogs, count, pageParams.getPageSize(), pageParams.getPageNum());
	  }

	  private void populate(List<Blog> blogs) {
	    if (!blogs.isEmpty()) {
	      blogs.stream().forEach(item -> {
	        String stripped =  Jsoup.parse(item.getContent()).text();
	        item.setDigest(stripped.substring(0, Math.min(stripped.length(),40)));
	        String tags = item.getTags();
	        item.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
	        System.out.println(item);
	      });
	    }
	    System.out.println(blogs);
	  }

	  public Blog queryOneBlog(int id) {
	    Blog query = new Blog();
	    query.setId(id);
	    List<Blog> blogs = blogMapper.selectBlog(query, new PageParams(1, 1));
	    if (!blogs.isEmpty()) {
	      return blogs.get(0);
	    }
	    return null;
	  }

	
	
	
	
}
