package com.epxing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epxing.entity.Pagination;
import com.epxing.entity.WebsiteInformationContent;
import com.epxing.service.InfoService;

@Controller
public class InfoController {
	
	@Autowired
	private InfoService infoServiceImpl;
	
	
	/**
	 * 获取列表信息
	 * @param page
	 * @param size
	 * @param ownModule
	 * @return
	 */
	@RequestMapping(value="getNoticeInfo",method=RequestMethod.POST)
	@ResponseBody
	public String getNoticeInfo(String page,String size,String ownModule){
		int count = infoServiceImpl.getCount(ownModule);
		Pagination<WebsiteInformationContent> p = new Pagination<WebsiteInformationContent>(page,size,count);
		String content = infoServiceImpl.findInfoByPageAndOwnModule(p,ownModule );
		String info = infoServiceImpl.pageGetInfo(p,content);
		return info;
	}
	
	/**
	 * 获取详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getDetailedInfo",method=RequestMethod.POST)
	@ResponseBody
	public String detailedInfo(String id,String ownModule){
		return infoServiceImpl.findDetailInfo(id,ownModule);
	}
	
	
	
}
