package com.epxing.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.epxing.entity.WebUser;
import com.epxing.mapper.UserMapper;
import com.epxing.service.UserService;
import com.epxing.util.MessageCenter;

import net.sf.json.JSONObject;


@Service
public class UserServiceImpl  implements UserService{
	
	
	private static final String  NO_ACCOUNT ="NO_ACCOUNT"; //账户不存在
	private static final String  ERROR_UPDATE ="ERROR_UPDATE"; //操作更新信息失败
	private static final String  ERROR_SENDSMS ="ERROR_SENDSMS"; //短信发送失败
	private static final String  SUCCESS_UPDATE ="SUCCESS_UPDATE"; //操作更新信息失败
	private static final String  SUCCESS_SENDSMS ="SUCCESS_SENDSMS"; //短信发送成功
	private static final String  OUT_TIMES ="OUT_TIMES"; //超出每日最大修改数量
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public WebUser login(String ACCOUNT, String PASSWORD) {
		
		return userMapper.findByAccountAndPassword(ACCOUNT,PASSWORD);
	}

	@Override
	public int register(String account ,String password  ) {
		
		return userMapper.insertUser(account,password);
	}

	@Override
	public int passwordRetrieval(String realName, String password,String idCard) {
		
		return userMapper.updateAccountAndPassword(realName,password,idCard);
	}
	
	@Override
	public boolean checkAccount(String account) {
		
		return userMapper.findByAccount(account)>0?true:false;
	}

	@Override
	public int consummateInfo(WebUser user) {
		
		return userMapper.updateInfo(user);
	}

	@Override
	public String getPasswordByPhone(String account, String phone) {
		
		WebUser user = userMapper.queryByAccount(account);
		java.util.Date curDate = new java.util.Date();
		java.sql.Date date = new java.sql.Date(curDate.getTime());
		String password =user.getAccount()+"ypxing";
		
		if(user != null && user.getRealName() != null){
			int updateTimes = getPasswordUpdateTimes(user.getAccount(),date);
			if(updateTimes<=5){
				int i = userMapper.updateAccountAndPassword(user.getRealName(),password,user.getIdCard());
				if(i>0){
					MessageCenter messagetool = new MessageCenter();
					try {
						JSONObject obj = messagetool.sendSMS(new String[]{phone}, password, user.getRealName());
						 if(null !=obj && "SUCCESS".equals(obj.getString("STATUS"))){
							 return SUCCESS_SENDSMS;
						 }else{
							 return ERROR_SENDSMS;
						 }
					} catch (Exception e) {
						e.printStackTrace();
						return  ERROR_UPDATE;
					}
				}else{
					return  ERROR_UPDATE;
				}
			}else{
				return OUT_TIMES;
			}
		}else{
			return NO_ACCOUNT;
		}
	}
	
	public int getPasswordUpdateTimes(String account ,java.sql.Date date){
		
		return userMapper.updatePasswordTimes(account, date);
	}

	@Override
	public int updateInfo(WebUser user) {
		
		return userMapper.updateInfo(user);
	}

	@Override
	public boolean checkPassword(String account, String password) {
		WebUser login = login(account,password);
		if(login !=null){
			return true;
		}
		return false;
	}
	
	
	
	
} 
