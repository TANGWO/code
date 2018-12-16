package com.epxing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.epxing.entity.Course;
import com.epxing.entity.CourseCatalogue;
import com.epxing.entity.MyCourse;
import com.epxing.entity.Pagination;

public interface CourseMapper {
	
	List<Course>getCourseList(@Param("p")Pagination<Course> p);
	
	
	@Select("select count(cid) from Course ")
	int getCount();

	@Select("select count(id) from MyCourse ")
	int getMyCourseCount();

	List<CourseCatalogue> getCatalogueById(@Param("cid")String cid);

	
	List<CourseCatalogue> getCoursrByUser(@Param ("UserId")Integer UserId);
	
	
	List<MyCourse> getMyCourseByUser(@Param ("uid")Integer UserId,@Param("p")Pagination<MyCourse> p,@Param("status")String status);
	
	
	
}
