package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class House_Msg {
	
	private Integer id;
	private String msg;
	private Date create_time;
	private Integer agent_id;
	private Integer house_id;
	private String user_name;
	
}
