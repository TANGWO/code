package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	 
	  private Long id;
	  private String content;
	  private Long   houseId;
	  private Date   createTime;
	  private Integer blogId;
	  private Integer type;
	  private Long    userId;
	  
	  private String  userName;
	  private String  avatar;
	
}
