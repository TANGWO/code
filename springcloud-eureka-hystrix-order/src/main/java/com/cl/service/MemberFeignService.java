package com.cl.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;



@FeignClient(value="service-member")
public interface MemberFeignService {
	
	
	@RequestMapping("/getUser")
	public JSONObject getUserMapToMember();

	
}
