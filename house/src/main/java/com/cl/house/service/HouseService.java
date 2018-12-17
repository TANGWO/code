package com.cl.house.service;

import java.util.List;

import com.cl.house.common.constants.HouseUserType;
import com.cl.house.common.model.Community;
import com.cl.house.common.model.House;
import com.cl.house.common.model.HouseUser;
import com.cl.house.common.model.User;
import com.cl.house.common.model.UserMsg;
import com.cl.house.common.page.PageData;
import com.cl.house.common.page.PageParams;

public interface HouseService {

	PageData<House> queryHouse(House query, PageParams build);

	/**
	 * @param id
	 * @param id2
	 * @param bookmark
	 */
	void unbindUser2House(Long id, Long id2, HouseUserType bookmark);

	/**
	 * @return
	 */
	List<Community> getAllCommunitys();

	/**
	 * @param house
	 * @param user
	 */
	void addHouse(House house, User user);

	/**
	 * @param id
	 * @return
	 */
	House queryOneHouse(Long id);

	/**
	 * @param id
	 * @return
	 */
	HouseUser getHouseUser(Long id);

	/**
	 * @param userMsg
	 */
	void addUserMsg(UserMsg userMsg);

	/**
	 * @param id
	 * @param rating
	 */
	void updateRating(Long id, Double rating);

	/**
	 * @param id
	 * @param id2
	 * @param b
	 */
	void bindUser2House(Long id, Long id2, boolean b);

	/**
	 * @param query
	 * @param pageParams
	 * @return
	 */
	List<House> queryAndSetImg(House query, PageParams pageParams);


	
	
	
	
	
}
