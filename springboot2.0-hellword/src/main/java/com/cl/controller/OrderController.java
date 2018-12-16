package com.cl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	
}
