package com.cl.service.impl;

import org.springframework.stereotype.Service;

import com.cl.service.IndexService;


@Service
public class IndexServiceImpl implements IndexService {

	@Override
	public String index() {
		return "��ӭ���������������ͣ��ٻ�ʦ��";
	}

}
