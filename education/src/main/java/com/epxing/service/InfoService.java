package com.epxing.service;

import java.util.List;

import com.epxing.entity.Pagination;
import com.epxing.entity.WebsiteInformationContent;

public interface InfoService {
	
	int getCount(String column);
	
	String findInfoByPageAndOwnModule(Pagination<WebsiteInformationContent> p,String ownModule);
		
	String findDetailInfo(String id,String ownModule);
	
	String pageGetInfo(Pagination<WebsiteInformationContent> p,String context);
}
