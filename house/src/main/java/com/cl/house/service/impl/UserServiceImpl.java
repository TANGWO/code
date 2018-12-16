package com.cl.house.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cl.house.common.model.User;
import com.cl.house.mapper.UserMapper;
import com.cl.house.service.UserService;

@Service
public class UserServiceImpl  implements UserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> selectUser() {
		
		return userMapper.selectUser();
	}

}
