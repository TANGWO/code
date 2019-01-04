package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.CallbackInfo;

public interface ICallbackDao extends JpaRepository<CallbackInfo, Long> {
	
	public CallbackInfo findByGoodsBillIdAndType(String goodsBillId, String type);
	
}
