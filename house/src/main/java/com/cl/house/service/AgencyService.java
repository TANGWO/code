 package com.cl.house.service;

import java.util.List;

import com.cl.house.common.model.Agency;
import com.cl.house.common.model.User;
import com.cl.house.common.page.PageData;
import com.cl.house.common.page.PageParams;

/**
 * @author chenling
 * @date 2018/12/17
 */
public interface AgencyService {

	/**
	 * @param agentId
	 * @return
	 */
	User getAgentDeail(Long agentId);

	/**
	 * @param agency
	 */
	int add(Agency agency);

	/**
	 * @return
	 */
	List<Agency> getAllAgency();

	/**
	 * @param id
	 * @return
	 */
	Agency getAgency(Integer id);

	/**
	 * @param build
	 * @return
	 */
	PageData<User> getAllAgent(PageParams build);
	
	
	
	
}
