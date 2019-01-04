 package com.cl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cl.entity.Agency;
import com.cl.entity.AgencyExample;
import com.cl.entity.AgencyExample.Criteria;
import com.cl.mapper.AgencyMapper;
import com.cl.service.AgencyService;
import com.google.common.collect.Lists;

/**
 * @author chenling
 * @date 2019/01/03
 */
@Service
public class AgencyServiceImpl implements AgencyService {
   
	@Resource
	private AgencyMapper agencyMapper;
	
	/**
	 * 查询所有
	 */
	public String findAgency() {
		AgencyExample agencyExample = new AgencyExample();
		//Criteria criteria = agencyExample.createCriteria();
		List<Agency> list = agencyMapper.selectByExample(agencyExample);
		System.out.println(list);
		String string = JSONObject.toJSONString(list);
		return string;
	}
	/**
	 * 通过条件查询
	 */
	public String findAgencyByName(String name) {
		AgencyExample agencyExample = new AgencyExample();
		Criteria criteria = agencyExample.createCriteria();
		criteria.andNameEqualTo(name);
		List<Agency> list = agencyMapper.selectByExample(agencyExample);
		System.out.println(list);
		String string = JSONObject.toJSONString(list);
		return string;
	}
	
	/**
	 * 增加数据
	 */
	public String addAgencyByOneObject(Agency agency) {
		int i = agencyMapper.insertSelective(agency);
		return i>0?"save success": "save failure";
	}
	/**
	 * 通过ID查询
	 */
	public String findById(Integer id) {
		AgencyExample agencyExample = new AgencyExample();
		Criteria criteria = agencyExample.createCriteria();
		criteria.andIdEqualTo(id);
		List<Agency> list = this.agencyMapper.selectByExample(agencyExample);
		String string = JSONObject.toJSONString(list);
		return string;
	}
	/**
	 * 通过主键查询
	 */
	public String findByPrimaryKey(Integer id) {
		AgencyExample agencyExample = new AgencyExample();
		Criteria criteria = agencyExample.createCriteria();
		criteria.andIdEqualTo(id);
		Agency selectByPrimaryKey = this.agencyMapper.selectByPrimaryKey(id);
		String string = JSONObject.toJSONString(selectByPrimaryKey);
		return string;
	}
	
	/**
	 * 排序
	 */
	public String findByOrder(String orderField,String orderModle) {
		AgencyExample agencyExample = new AgencyExample();
		String order=" order by "+orderField+" "+ orderModle;
		agencyExample.setOrderByClause(order);
	    List<Agency> list = this.agencyMapper.selectByExample(agencyExample);
		String string = JSONObject.toJSONString(list);
		return string;
	}
	
	/**
	 * 查找使用in
	 */
	public String findByIn(Integer value) {
		AgencyExample agencyExample = new AgencyExample();
		Criteria criteria = agencyExample.createCriteria();
		criteria.andIdIn(Lists.newArrayList(value));
		List<Agency> list = this.agencyMapper.selectByExample(agencyExample);
		String string = JSONObject.toJSONString(list);
		return string;
	}
	
	/**
	 * 1.按照ID排序
	 * 2.姓名等于chenling
	 * 3.电话不等于1
	 */
	public String findByMore() {
		AgencyExample agencyExample = new AgencyExample();
		Criteria criteria = agencyExample.createCriteria();
		agencyExample.setOrderByClause("ID DESC");
		criteria.andNameNotEqualTo("chenling");
		criteria.andPhoneNotEqualTo("1");
		agencyExample.or().andAddressNotLike("广州");
		List<Agency> list = this.agencyMapper.selectByExample(agencyExample);
		String string = JSONObject.toJSONString(list);
		return string;
	}
}
