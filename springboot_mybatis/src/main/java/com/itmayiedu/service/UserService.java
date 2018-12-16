package com.itmayiedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itmayiedu.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public int insertUser(int id,String userName,String passWord,String realName){
		int insertUserResult = userMapper.insert(id, userName, passWord, realName);
		System.out.println("---------"+insertUserResult);
		return insertUserResult;
		
	}

}
