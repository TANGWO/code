package com.cl.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.entity.FM_USER;
import com.cl.mapper.UserMapper;
import com.cl.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String findUserById(Integer id) {
		
		ObjectMapper mapper = new ObjectMapper();
		FM_USER user = userMapper.findUserById(id);
		try {
			String string = mapper.writeValueAsString(userMapper.findUserById(id));
			log.info(string);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return user.toString();
	}

}
