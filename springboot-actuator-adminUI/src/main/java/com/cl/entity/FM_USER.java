package com.cl.entity;

import lombok.Data;
import oracle.sql.TIMESTAMP;


@Data
public class FM_USER {
	
	private String NAME;
	private String PASSWORD;
	private String ACCOUNT;
	private Integer ID_;
	private String EMP_ID;
	private TIMESTAMP LAST_LOGIN_TIME_;
	private String DEPT_ID_;
	private String USER_TYPE_;
	private String STATUS_;
	private String PASSWORD_FLAG_;
	
	
}
