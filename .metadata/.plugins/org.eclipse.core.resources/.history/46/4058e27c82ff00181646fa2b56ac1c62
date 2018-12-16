package com.cl.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cl.service.MemberFeignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FeignController {
   
	@Autowired
	private MemberFeignService memberFeignService;
	
	
	@RequestMapping("/feignGetMember")
	public List<String> getUserToMember(){
		System.out.println("feign 工程调用member 工程");
		return memberFeignService.getUserToMember();
	}
	
	@RequestMapping("/getOrderInfo")
	public String getOrderInfo() {
		log.info("****************服务降级执行************");
		return "。。。。";
	}
	
	/**
	  * 订单调用会员服务，解决雪崩效应，底层使用服务隔离，线程池方式实现
	 * @return
	 */
	@RequestMapping("/feignHystrixGetMember")
	public List<String> getUserToHystrixMember(){
		JSONObject member = new JSONObject();
		System.out.println("feign 工程调用member 工程");
		return memberFeignService.getUserToMember();
	}
	
	
}
