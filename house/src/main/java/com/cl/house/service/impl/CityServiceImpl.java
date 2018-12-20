 package com.cl.house.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cl.house.common.model.City;
import com.cl.house.service.CityService;
import com.google.common.collect.Lists;

/**
 * @author chenling
 * @date 2018/12/17
 */
@Service
public class CityServiceImpl implements CityService {

	/* (non-Javadoc)
	 * @see com.cl.house.service.CityService#getAllCitys()
	 */
	@Override
	public List<City> getAllCitys(){
	    City city = new City();
	    city.setCityCode("110000");
	    city.setCityName("北京");
	    city.setId(1);
	    return Lists.newArrayList(city);
    }

}
