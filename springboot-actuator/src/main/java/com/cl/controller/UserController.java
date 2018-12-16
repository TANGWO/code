package com.cl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cl.source01.service.User1Service;
import com.cl.source02.service.User2Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController  {
   
	
	@Autowired
	private User1Service user1Service;
	@Autowired
	private User2Service user2Service;
	
	@Value("${spring.datasource.source2.jdbc-url}")
	private String info;
	
	@RequestMapping(value="/getUser1")
	public String getUser1(Integer id ) {
		log.info("获取数据信息为："+this.info);
		return user1Service.findUserById(id);
	}
	
	@RequestMapping(value="/getUser2")
	public String getUser2(Integer id ) {
		
		return user2Service.findUserById(id);
	}
	
	@RequestMapping(value="/getUser3")
	public String getUser3(Integer id ) {
		
		return user2Service.findUserById(id);
	}
	
	@RequestMapping(value="/getUser4")
	public String getUser4(Integer id ) {
		return user2Service.findUserById(id);
	}
	
	
}
