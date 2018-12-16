package com.epxing.service;

import com.epxing.entity.Course;
import com.epxing.entity.MyCourse;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebUser;

public interface CourseService {

	int getCount();

	String  getCourseList(Pagination<Course> p);

	String getCatalogueById(String id);

	String pageGetInfo(Pagination<Course> p,String context);
	
	String getCourseByUser(WebUser user,Pagination<MyCourse> p,String status);
	
	int getMyCourseCount();
	
}
