 package com.cl.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cl.house.common.constants.CommonConstants;
import com.cl.house.common.model.Blog;
import com.cl.house.common.model.Comment;
import com.cl.house.common.model.House;
import com.cl.house.common.page.PageData;
import com.cl.house.common.page.PageParams;
import com.cl.house.service.BlogService;
import com.cl.house.service.CommentService;
import com.cl.house.service.RecommendService;

/**
 * @author chenling
 * @date 2018/12/17
 */
@Controller
public class BlogController {
	 @Autowired
	  private BlogService blogService;
	  
	  @Autowired
	  private CommentService commentService;
	  
	  @Autowired
	  private RecommendService recommendService;
	  
	  @RequestMapping(value="blog/list",method={RequestMethod.POST,RequestMethod.GET})
	  public String list(Integer pageSize,Integer pageNum,Blog query,ModelMap modelMap){
	    PageData<Blog> ps = blogService.queryBlog(query,PageParams.build(pageSize, pageNum));
	    List<House> houses =  recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
	    modelMap.put("recomHouses", houses);
	    modelMap.put("ps", ps);
	    return "/blog/listing";
	  }
	  
	  @RequestMapping(value="blog/detail",method={RequestMethod.POST,RequestMethod.GET})
	  public String blogDetail(int id,ModelMap modelMap){
	    Blog blog = blogService.queryOneBlog(id);
	    List<Comment> comments = commentService.getBlogComments(id,8);
	    List<House> houses =  recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
	    modelMap.put("recomHouses", houses);
	    modelMap.put("blog", blog);
	    modelMap.put("commentList", comments);
	    return "/blog/detail";
	  }
	
	
	
	
	
}
