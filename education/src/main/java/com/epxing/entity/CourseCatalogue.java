package com.epxing.entity;

import lombok.Data;

/**
 * 课程列表
 * @author chenl
 *
 */
@Data
public class CourseCatalogue {
	
	private Integer ccid;
	private String  catalogueName;//目录名称
	private Integer courseTime;   //课程时长
	private Course  ownCourse;    //所属课程
	private FM_BLOB video;  //课程视频
}
