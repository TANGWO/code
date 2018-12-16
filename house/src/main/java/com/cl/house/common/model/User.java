package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
	private Integer id;
	private String name;
	private String phone;
	private String email;
	private String aboutme;
	private String passwd;
	private String avatar;
	private Integer type;
	private Date create_time;
	private Integer enable;
	private Integer agency_id;
	
}
