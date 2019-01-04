package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.CityExpand;

public interface ICityExpandDao extends JpaRepository<CityExpand, Long> {
	
	public CityExpand findByCityId(int cityId);
	
	public CityExpand findById(int id);

}
