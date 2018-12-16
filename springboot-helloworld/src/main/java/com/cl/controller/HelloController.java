package com.cl.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {
	
	@RequestMapping("/indexs")
	public String index() {
		
		return "HelloWorldSpringBoot";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		
		return "HelloWorld";
	}
	
	
	public static void main(String[] args) {
		
		//SpringApplication.run(HelloController.class, args);
		
	}
}
