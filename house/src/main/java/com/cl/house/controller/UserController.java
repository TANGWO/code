package com.cl.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.house.common.model.User;
import com.cl.house.service.UserService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/selectUser")
	@ResponseBody
	public List<User> selectUser(){
		
		return userService.selectUser();
	}
	
	
}
