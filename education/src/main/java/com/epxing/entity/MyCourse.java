package com.epxing.entity;


import lombok.Data;
@Data
public class MyCourse {
	private Integer id;
	private WebUser webUser;       //用户ID
	private Course  course;     //课程ID
	private String  status;       //课程状态 
}
