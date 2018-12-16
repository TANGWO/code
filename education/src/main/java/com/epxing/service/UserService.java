package com.epxing.service;

import com.epxing.entity.WebUser;

public interface UserService {

	 //用户登录
	 WebUser login(String ACCOUNT, String PASSWORD);

	 //用户注册
	 int register(String account,String  password);
	 
	 //找回密码
	 int passwordRetrieval(String realName,String password,String idCard);
	 
	 //验证用户名
    boolean checkAccount(String account);

    //完善个人信息
	int consummateInfo(WebUser user);
	
	//通过手机找回密码
	String getPasswordByPhone(String account, String phone);
	
	//修改个人信息
	int updateInfo(WebUser user);
	
	//验证密码
	boolean checkPassword(String account,String password);
}
