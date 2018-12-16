package com.cl.service.impl;

import org.springframework.stereotype.Service;

import com.cl.service.IndexService;


@Service
public class IndexServiceImpl implements IndexService {

	@Override
	public String index() {
		return "欢迎来到得来联盟世纪，召唤师！";
	}

}
