 package com.cl.house.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cl.house.common.model.Agency;
import com.cl.house.common.model.User;
import com.cl.house.common.page.PageParams;

/**
 * @author chenling
 * @date 2018/12/17
 */
public interface AgencyMapper {

	/**
	 * @param user
	 * @param pageParams
	 * @return
	 */
	List<User> selectAgent(User user, PageParams pageParams);

	/**
	 * @param agency
	 * @return
	 */
	List<Agency> select(Agency agency);

	/**
	 * @param user
	 * @return
	 */
	Long selectAgentCount(@Param("user")User user);

	/**
	 * @param agency
	 * @return
	 */
	int insert(Agency agency);

}
