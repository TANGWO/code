package com.cl.house.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class HouseUser {
	private Long id;
	private Long houseId;
	private Long userId;
	private Date  createTime;
	private Integer type;
}
