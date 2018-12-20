 package com.cl.house.service;

import java.util.List;

import com.cl.house.common.model.House;

/**
 * @author chenling
 * @date 2018/12/17
 */
public interface RecommendService {

	/**
	 * @param recomSize
	 * @return
	 */
	List<House> getHotHouse(Integer recomSize);

	/**
	 * @param id
	 */
	void increase(Long id);

	/**
	 * @return
	 */
	List<House> getLastest();

	
	
}
