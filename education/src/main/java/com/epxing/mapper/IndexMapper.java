package com.epxing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.epxing.entity.Course;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebsiteInformationContent;
public interface IndexMapper {
	
	List<WebsiteInformationContent>  findNoticeByCode(@Param("p")Pagination<WebsiteInformationContent> p,@Param("ownModule")String ownModule);
    
	@Select("select count(*) from WebsiteInformationContent where ownModule =#{ownModule}")
	int  findCount(@Param("ownModule")String ownModule);
	
}
