package com.cl.house.common.model;

import lombok.Data;

@Data
public class Blog {
	
   private Integer id;
   private String tags;
   private String content;
   private Data create_time;
   private String title;
   private Integer cat;
   
	
}
