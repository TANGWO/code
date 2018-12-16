package com.epxing.entity;


import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 在线课程
 * @author chenl
 *
 */
@Setter
@Getter
@ToString
public class Course {
	
	private Integer cid;       //课程编号        
	private String  courseName;//课程名称
	private String  courseBriefIntroduction;//课程简介 
	private Date uploadTime;//上传时间
	private List<CourseCatalogue> courseVideo; //课程视频
	private FM_BLOB cover ;//封面
	private WebUser speaker;//主讲人
}
