package com.epxing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epxing.entity.Course;
import com.epxing.entity.MyCourse;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebUser;
import com.epxing.service.CourseService;

@Controller
public class CourseController {
   
	@Autowired
	private CourseService courseServiceImpl;
	
	
	@RequestMapping(value="getSimpleCourse",method=RequestMethod.POST)
	@ResponseBody
	public String getSimpleCourse(String page,String size){
		int count = courseServiceImpl.getCount();
		Pagination<Course> p = new Pagination<Course>(page,size,count);
		String context = courseServiceImpl.getCourseList(p);
		String pageGetInfo = courseServiceImpl.pageGetInfo(p, context);
		return pageGetInfo;
	}
	
	/**
	 * 根据课程ID，获取课程目录
	 */
	
	@RequestMapping(value="getCatalogueById",method=RequestMethod.POST)
	@ResponseBody
	public String getCatalogueById(String id){
		return courseServiceImpl.getCatalogueById(id);
	}
	
	
	/**
	 * 获取我的课程
	 */
	@RequestMapping(value="myCourse",method=RequestMethod.POST)
	@ResponseBody
	public String getMyCourse(HttpServletRequest request,HttpServletResponse response,String page,String size,String status){
		WebUser user = (WebUser)request.getSession().getAttribute("user");
		if(user==null){
			return  "index";
		}
		int count = courseServiceImpl.getMyCourseCount();
		Pagination<MyCourse> p = new Pagination<MyCourse>(page,size,count);
		String context = courseServiceImpl.getCourseByUser(user,p,status);
		return context;
	}
	
}
