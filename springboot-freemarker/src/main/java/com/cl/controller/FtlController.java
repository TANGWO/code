package com.cl.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FtlController {

	@RequestMapping(value="/getpage1")
	public String getPage1(Map<String,Object> map) {
		map.put("name", "陈玲");
		map.put("sex", "男");
		map.put("age", 24);
		map.put("height", 1.68);
		return "index";
		
	}
	@RequestMapping(value="/getpage")
	public String getPage(Map<String,Object> map) {
		map.put("name", "召唤师");
		map.put("sex", "男");
		map.put("age", 24);
		map.put("height", 1.68);
		log.info("欢迎来到召唤师峡谷，召唤师");
		return "page";
		
	}
}
