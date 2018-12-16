package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class House {
		
	private  Integer    id;
	private  String     name;
	private  Integer    type;
	private  Integer    price;
	private  String     images;
	private  Integer    area;
	private  Integer    beds;
	private  Integer    baths;
	private  Double    rating;
	private  String    remarks;
	private  String    properties;
	private  String    floor_plan;
	private  String    tags;
	private  Date    create_time;
	private  Integer    city_id;
	private  Integer    community_id;
	private  String    address;
	private  Integer    state;
}
