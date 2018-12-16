package com.cl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringMvcController {
	
	@RequestMapping("/index")
	public String index() {
		return "SpringMvc ÷–¥øÚº‹";
	}
	
}
