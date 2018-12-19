package com.cl.house.mapper;

import java.util.List;


import com.cl.house.common.model.User;


public interface UserMapper {

	public List<User> selectUser ();

	public int insert(User user);

	public int delete(String email);

	public int update(User user);

	public List<User> selectUserByQuery(User user);
	
}
