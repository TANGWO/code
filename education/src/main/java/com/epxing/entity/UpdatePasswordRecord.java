package com.epxing.entity;

import java.sql.Date;

import lombok.Data;


@Data	
public class UpdatePasswordRecord {
	
	private Integer uprid;     
	private Integer Times;  //修改次数
	private Date  update_date;     //修改时间
	private WebUser web_user ;   //修改人
	private String status;   //修改状态

}
