package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	 
	private Integer id;
	private String content;
	private Integer house_id;
	private Date create_time;
	private Integer blog_id;
	private Integer type;
	private Integer user_id;
	
}
