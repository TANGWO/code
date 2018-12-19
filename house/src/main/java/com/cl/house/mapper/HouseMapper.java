package com.cl.house.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cl.house.common.model.Community;
import com.cl.house.common.model.House;
import com.cl.house.common.model.HouseUser;
import com.cl.house.common.model.User;
import com.cl.house.common.model.UserMsg;
import com.cl.house.common.page.PageParams;


public interface HouseMapper {
    public List<House>  selectPageHouses(@Param("house")House house,@Param("pageParams")PageParams pageParams);
    
    public Long selectPageCount(@Param("house") House house);
	
	public int insert(User account);

	public List<Community> selectCommunity(Community community);

	public int insert(House house);

	public HouseUser selectHouseUser(@Param("userId")Long userId,@Param("id") Long houseId,@Param("type") Integer type);
	
	public HouseUser selectSaleHouseUser(@Param("id") Long id);

	public int insertHouseUser(HouseUser houseUser);

	public int insertUserMsg(UserMsg userMsg);

	public int updateHouse(House updateHouse);
	
	public  int downHouse(Long id);

	public int deleteHouseUser(@Param("id")Long id,@Param("userId") Long userId,@Param("type") Integer type);
	
	
	
}
