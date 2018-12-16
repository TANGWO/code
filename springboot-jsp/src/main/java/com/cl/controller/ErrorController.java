package com.cl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
	
	
	@RequestMapping(value="/getResult")
	public String getResult(int i) {
		int r = 1/i;
		return "success: result is "+r;
	}
	
}
