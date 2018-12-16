package com.epxing.entity;


import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class WebsitePicture {
	
	private Integer wpid;
	private String pictureName;
	private FM_BLOB webPicture;
	private Date updateLoadTime;
}
