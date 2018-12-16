package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class House_User {
	
	private Integer id;
	private Integer house_id;
	private Integer user_id;
	private Date create_time;
	private Integer type;
}
