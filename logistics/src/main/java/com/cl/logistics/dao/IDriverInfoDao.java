package com.cl.logistics.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cl.logistics.bean.DriverInfo;

public interface IDriverInfoDao extends JpaRepository<DriverInfo, Long> {

	public DriverInfo findById(String id);
	
	@Query(value = "select id from driverinfo")
	public List<String> findAllId();
	
}
