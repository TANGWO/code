package com.cl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cl.service.IndexService;

@RestController
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	
	@RequestMapping(value="/index",produces="text/html;charset=UTF-8")
	public String index() {
		return indexService.index();
	}

	

}
