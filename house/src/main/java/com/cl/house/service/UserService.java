package com.cl.house.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cl.house.common.model.User;

public interface UserService {

	List<User> selectUser();
	
	@Transactional(rollbackFor=Exception.class)
	boolean addUser(User user);

	boolean enable(String key);

	User auth(String username, String password);

	/**
	 * @param key
	 * @return
	 */
	String getResetEmail(String key);

	/**
	 * @param updateUser
	 * @param email
	 */
	void updateUser(User updateUser, String email);

	/**
	 * @param query
	 * @return
	 */
	List<User> getUserByQuery(User query);

	/**
	 * @param username
	 */
	void resetNotify(String username);

	/**
	 * @param key
	 * @param passwd
	 * @return
	 */
	User reset(String key, String passwd);

	/**
	 * @param userId
	 * @return
	 */
	User getUserById(Long userId);

	
}
