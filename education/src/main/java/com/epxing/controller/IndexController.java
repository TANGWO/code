package com.epxing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epxing.service.IndexService;
import com.epxing.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private IndexService indexServiceImpl;
	
	
	 /**	
	  * 获取首页通知公告
	  */
	@RequestMapping(value="getNotice" ,method=RequestMethod.POST)
	@ResponseBody
	public String getNotice(){
		return indexServiceImpl.getNotice();
	}
	
	@RequestMapping(value="/")
	public String toIndex(){
		return "index";
	}
	
}
