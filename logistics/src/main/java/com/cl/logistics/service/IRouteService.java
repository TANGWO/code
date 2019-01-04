package com.cl.logistics.service;

import java.util.List;

import com.cl.logistics.bean.RouteInfo;

public interface IRouteService {
	
	public void generateRoute();
	
	public List<RouteInfo> findAllRouteInfos();

}
