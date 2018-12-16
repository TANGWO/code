package com.epxing.entity;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oracle.sql.CLOB;


  /***
   * 网站文章
   * @author chenl
   */

@Setter
@Getter
@ToString
public class WebsiteInformationContent {
	
	private Integer wicid;    //流水号
	private String title;   //标题
	private String  content;   //内容 CONTENT
	private FM_BLOB photo;  // 网站图片
	private Date releaseDate;//发布日期
	private Integer readingQuantity;//阅读数量
	private ColumnInformation ownModule;  //所属模块
	
	

}
