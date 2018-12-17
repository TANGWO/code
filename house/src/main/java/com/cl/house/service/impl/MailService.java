package com.cl.house.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cl.house.common.model.User;
import com.cl.house.mapper.UserMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	
	@Resource
	private UserMapper userMapper;
	
	
	@Value("${domain.name}")
	private String domainName;
	
    //邮件缓存
	private final Cache<String,String>  registerCache = CacheBuilder
				.newBuilder()
				.maximumSize(100)
				.expireAfterAccess(15, TimeUnit.MINUTES)
				.removalListener(new RemovalListener<String, String>() {
				    // 添加缓存删除事件
					@Override
					public void onRemoval(RemovalNotification<String, String> notification) {
						//优化，删除前先判断是否激活
						userMapper.delete(notification.getValue());
					}
				}).build();

	private final Cache<String, String> resetCache =  CacheBuilder.newBuilder()
			.maximumSize(100)
			.expireAfterAccess(15, TimeUnit.MINUTES)
			.build();
	
	public void sendMail(String title, String url, String email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(email);
		mailMessage.setText(url);
		mailMessage.setSubject(title);
		mailSender.send(mailMessage);
	}
	
	/**
	 * 1. 缓存key-email关系发送邮件
	 * 2. 借助spring email 
	 * 3. 借助异步框架进行异步操作
	 */
	@Async
	public void registerNotify(String email) {
		String randomKey = RandomStringUtils.randomAlphabetic(10);
		registerCache.put(randomKey, email);
		
		//构建邮件发送地址
		String url = "http://"+domainName+"/accounts/verify?key="+randomKey;
		//发送邮件
		sendMail("房产平台激活邮件",url,email);
	}
	
	
      /**
	      * 发送重置密码邮件
	   * @param email
	   */
	  @Async
	  public void resetNotify(String email) {
	    String randomKey = RandomStringUtils.randomAlphanumeric(10);
	    resetCache.put(randomKey, email);
	    String content = "http://" + domainName + "/accounts/reset?key=" + randomKey;
	    sendMail("房产平台密码重置邮件", content, email);
	  }

	public boolean enable(String key) {
		
		String email = registerCache.getIfPresent(key);
		if(StringUtils.isBlank(email)) {
			return false;
		}
		User updateUser = new User();
		updateUser.setEmail(email);
		updateUser.setEnable(1);
		userMapper.update(updateUser);
		registerCache.invalidate(key);
		return true;
	}

	public String getResetEmail(String key){
	    return resetCache.getIfPresent(key);
	  }
	  
	  public void invalidateRestKey(String key){
	    resetCache.invalidate(key);
	  }

}
