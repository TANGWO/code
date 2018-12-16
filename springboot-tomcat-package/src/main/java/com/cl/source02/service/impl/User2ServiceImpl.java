package com.cl.source02.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cl.entity.FM_USER;
import com.cl.source02.mapper.User2Mapper;
import com.cl.source02.service.User2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class User2ServiceImpl implements User2Service {
	
	@Autowired
	private User2Mapper user2Mapper;
	
	@Override
	@Transactional(transactionManager="source2TransactionManager")
	public String findUserById(Integer id) {
		
		ObjectMapper mapper = new ObjectMapper();
		FM_USER user = user2Mapper.findUserById(id);
		try {
			String string = mapper.writeValueAsString(user2Mapper.findUserById(id));
			log.info(string);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return user.toString();
	}

}
