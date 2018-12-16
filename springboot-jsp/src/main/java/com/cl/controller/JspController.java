package com.cl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {
	
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	

}
