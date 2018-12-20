package com.cl.house.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cl.house.common.model.User;
import com.cl.house.common.utils.BeanHelper;
import com.cl.house.common.utils.HashUtils;
import com.cl.house.mapper.UserMapper;
import com.cl.house.service.UserService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;

@Service
public class UserServiceImpl  implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	@Autowired
	private FileService  fileService;
	
	@Value("${domain.name}")
	private String domainName;
	
	
	@Value("${file.prefix}")
	private String imgPrefix;
	
	@Autowired
	private MailService  mailService;
	
	
	
	@Override
	public List<User> selectUser() {
		
		return userMapper.selectUser();
	}

	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean addUser(User user) {
		user.setPasswd(HashUtils.encryPassword(user.getPasswd()));
		List<String> imgList = fileService.getImgPath(Lists.newArrayList(user.getAvatarFile()));
		if(!imgList.isEmpty()) {
			user.setAvatar(imgList.get(0));
		}
		BeanHelper.setDefaultProp(user, User.class);
		BeanHelper.onInsert(user);
		user.setEnable(0);
		userMapper.insert(user);
		mailService.registerNotify(user.getEmail());
		return true;
	}


	@Override
	public boolean enable(String key) {
		return mailService.enable( key) ;
	}


	@Override
	public User auth(String username, String password) {
		User user = new User();
		user.setEmail(username);
		user.setPasswd(HashUtils.encryPassword(password));
		user.setEnable(1);
		List<User> list = getUserByQuery(user);
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}


	public List<User> getUserByQuery(User user) {
		List<User> list = userMapper.selectUserByQuery(user);
		list.forEach(u->{
			u.setAvatar(imgPrefix+u.getAvatar());
		});
		return list;
	}

	 public void updateUser(User updateUser, String email) {
		    updateUser.setEmail(email);
		    BeanHelper.onUpdate(updateUser);
		    userMapper.update(updateUser);
		  }


		  public User getUserById(Long id) {
		    User queryUser = new User();
		    queryUser.setId(id);
		    List<User> users = getUserByQuery(queryUser);
		    if (!users.isEmpty()) {
		      return users.get(0);
		    }
		    return null;
		  }

		  public void resetNotify(String username) {
		    mailService.resetNotify(username);
		  }
		  
		  /**
		   * 重置密码操作
		   * @param email
		   * @param key
		   */
		  @Transactional(rollbackFor=Exception.class)
		  public User reset(String key,String password){
		    String email = getResetEmail(key);
		    User updateUser = new User();
		    updateUser.setEmail(email);
		    updateUser.setPasswd(HashUtils.encryPassword(password));
		    userMapper.update(updateUser);
		    mailService.invalidateRestKey(key);
		    return getUserByEmail(email);
		  }
		  
		  

		  public User getUserByEmail(String email) {
		    User queryUser = new User();
		    queryUser.setEmail(email);
		    List<User> users = getUserByQuery(queryUser);
		    if (!users.isEmpty()) {
		      return users.get(0);
		    }
		    return null;
		  }

		  public String getResetEmail(String key) {
		    String email = "";
		    try {
		      email =  mailService.getResetEmail(key);
		    } catch (Exception ignore) {
		    }
		    return email;
		  }




}
