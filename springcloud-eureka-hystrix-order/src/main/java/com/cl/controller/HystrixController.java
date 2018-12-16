package com.cl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cl.hystrix.OrderHystrixCommand;
import com.cl.service.MemberFeignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HystrixController {

	
	@Autowired
	private MemberFeignService memberFeignService;
	
	
	
	@RequestMapping("/getOrderInfo")
	public String getOrderInfo() {
		System.out.println("当前线程："+Thread.currentThread().getName());
		return "getOrderInfo";
	}
	
	/**
	  * 订单调用会员服务，解决雪崩效应，底层使用服务隔离，线程池方式实现
	 * @return
	 */
	@RequestMapping("/feignHystrixGetMember")
	public JSONObject getUserToHystrixMember(){
		 OrderHystrixCommand hystrixCommand = new OrderHystrixCommand(memberFeignService);
		 JSONObject result = hystrixCommand.execute();
		return result;
	}
	
}
