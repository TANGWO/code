package com.cl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cl.service.UserService;

@RestController
public class UserController {
   
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUser")
	public String getUser(Integer id ) {
		
		 return userService.findUserById(id);
		
	}
}
