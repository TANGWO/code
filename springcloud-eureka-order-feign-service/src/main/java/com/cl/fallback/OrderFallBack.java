package com.cl.fallback;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.cl.service.MemberFeignService;

@Component
public class OrderFallBack implements MemberFeignService {

	@Override
	public List<String> getUserToMember() {
		List<String> list = new ArrayList<String>();
		list.add("****************服务发生异常******************");
		return list;
	}

	@Override
	public JSONObject getUserMapToMember() {
		JSONObject result = new JSONObject();
		result.put("code", 500);
		result.put("msg", "****************服务发生异常******************");
		return result;
	}

}
