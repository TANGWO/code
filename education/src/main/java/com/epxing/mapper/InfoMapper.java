package com.epxing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.epxing.entity.ContactUs;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebsiteInformationContent;

public interface InfoMapper {
	
	@Select("select count(*) from  WebsiteInformationContent where ownModule =#{0}")
	int findCount(String column);

	List<WebsiteInformationContent> findInfoByPageAndOwnModule(@Param("p")Pagination<WebsiteInformationContent> p,  @Param("ownModule")String ownModule );

	WebsiteInformationContent findDetailInfo(@Param("wicid")String wicid,@Param("ownModule")String ownModule);
	
	@Select("select * from  ContactUs")
	ContactUs getLinkUs();
}
