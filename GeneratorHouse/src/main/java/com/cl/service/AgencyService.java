 package com.cl.service;

import com.cl.entity.Agency;

/**
 * @author chenling
 * @date 2019/01/03
 */
public interface AgencyService {
	
	 String findAgency();
	
	 String addAgencyByOneObject(Agency agency);
	 
	 String findAgencyByName(String name) ;
	 
	 String findByPrimaryKey(Integer id);
	 
	 String findById(Integer id);

	/**
	 * @return
	 */
	String findByMore();
	 
}
