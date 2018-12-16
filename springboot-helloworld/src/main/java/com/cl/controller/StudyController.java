package com.cl.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class StudyController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudyController.class);
	@RequestMapping("/do")
	public String getInfo() {
		logger.info("SpringBoot整合log4j");
		return "欢迎来到SpringBoot世界！";
	}
	
	@RequestMapping("/index")
	public String htmlInfo(Map<String,Object> map) {
		map.put("name", "陈领");
		map.put("sex", "0");
		map.put("birthday", "1994-06-16");
		return "index";
	}
	@RequestMapping("/sub")
	public String getSub(int num) {
		int s = 5;
		int w = s/num;
		return "success"+w;
	}
	@RequestMapping("/login")
	public String login(String name,int age) {
		
		return "success"+name+age;
	}
	
	
}
