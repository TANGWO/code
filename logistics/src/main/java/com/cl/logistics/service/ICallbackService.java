package com.cl.logistics.service;

import com.cl.logistics.bean.CallbackInfo;

public interface ICallbackService {
	
	public boolean addInfo(CallbackInfo callbackInfo);
	
	public CallbackInfo findDetail(String goodsBillId, String type);

}
