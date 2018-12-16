package com.cl.source01.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cl.entity.FM_USER;
import com.cl.source01.mapper.User1Mapper;
import com.cl.source01.service.User1Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class User1ServiceImpl implements User1Service {
	
	@Autowired
	private User1Mapper user1Mapper;
	
	
	@Override
	@Transactional(transactionManager="source1TransactionManager")
	public String findUserById(Integer id) {
		
		ObjectMapper mapper = new ObjectMapper();
		FM_USER user = user1Mapper.findUserById(id);
		try {
			String string = mapper.writeValueAsString(user);
			log.info(string);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return user.toString();
	}

}
