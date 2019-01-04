package com.cl.logistics.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.ProxyFeeClear;

public interface IProxyFeeClearDao extends JpaRepository<ProxyFeeClear, Long> {
	
	public ProxyFeeClear findByGoodsBillCode(String goodsBillCode);
	
	
	
}