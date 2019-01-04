package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.UserWithGroup;

public interface IUserWithGroupDao extends JpaRepository<UserWithGroup, Long> {

	public UserWithGroup findByUserId(String userId);
	
}
