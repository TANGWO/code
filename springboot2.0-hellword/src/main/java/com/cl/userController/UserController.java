package com.cl.userController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	
	@RequestMapping(value="/userIndex")
	public String getUser() {
		return "USER_INFO";
	}
}
