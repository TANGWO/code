package com.epxing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.epxing.entity.Course;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebsiteInformationContent;
import com.epxing.entity.WebsitePicture;
import com.epxing.mapper.AttachmentMapper;
import com.epxing.mapper.CourseMapper;
import com.epxing.mapper.IndexMapper;
import com.epxing.service.IndexService;
import com.epxing.util.JsonUtils;
@Service
public class IndexServiceImpl implements IndexService {

	@Resource
	private IndexMapper indexMapper;
	
	@Resource
	private CourseMapper courseMapper;
	
	@Resource
	private AttachmentMapper attachmentMapper;
	
	@Override
	public String getNotice() {
		String code [] ={"notice","dynamicsInformation","trainingProjects"};
		Map <String,Object> map = new HashMap<String, Object>();
		for(int i=0;i<code.length;i++){
			int totalSize = indexMapper.findCount(code[i]);
			Pagination<WebsiteInformationContent> p = new Pagination<>("1","6",totalSize);
			List<WebsiteInformationContent> entity = indexMapper.findNoticeByCode(p, code[i]);
			String div="";
			for(int j =0;j<entity.size();j++){
				String entitys="";
				if(i==0){
					entitys="notice";
				}else if(i==1){
					entitys="dynamicsInformation";
				}else{
					entitys="trainingProjects";
				}
				String div2 ="";
				String div1 =" <li class='clearfix'>";
				if(entity.get(j).getTitle().length()>=15){
					div2 ="  <a href='noticeTwo?code="+entity.get(j).getWicid()+"&modle="+entitys+"' class='weui-fl'>"+entity.get(j).getTitle().substring(0, 10)+"....</a>";
				}else{
					div2 ="  <a href='noticeTwo?code="+entity.get(j).getWicid()+"&modle="+entitys+"' class='weui-fl'>"+entity.get(j).getTitle()+"</a>";
				}
				String div3 =" <span class='weui-fr'> "+entity.get(j).getReleaseDate()+"</span>";
				String div4 =" </li>";
				div=div+div1+div2+div3+div4;
			}
			map.put(code[i], div);
		}
		
		
		//获取在线课程
		int totalSize = courseMapper.getCount();
		Pagination<Course> p = new Pagination<>("1","3",totalSize);
		List<Course> courseList = courseMapper.getCourseList(p);
		String div="";
		for(int j =0;j<courseList.size();j++){
			String div1="";
			if(j==0){
				div1="<li class='weui-fl'>";
			}else if(j==1){
				div1="<li class='weui-fl ml'>";
			}else if(j==2){
				div1="<li class='weui-fr ml'>";
			}
			
			String div2=" <a href='onlineCourseTwo?code="+courseList.get(j).getCid()+"' class='weui-db'><i></i><img src=down?id="+courseList.get(j).getCover().getID()+"></a>";
			String div3="  <p class='weui-pt15'>"+courseList.get(j).getCourseName()+"</p>";
			String div4=" </li>";
			div=div+div1+div2+div3+div4;
		}
		map.put("onlineCourse", div);
		
		//获取网站轮播图片
		
		int photoCount = attachmentMapper.getPhotoCount();
		Pagination<WebsitePicture> p1 = new Pagination<WebsitePicture>("1","3",totalSize);
		List<WebsitePicture> photoList = attachmentMapper.getPhotoList(p1);
		String siv="";
		if(photoList.size()>0){
			String siv1="";
			for(int i=0;i<photoList.size();i++){
				if(i==0){
					siv1="<div class='item active'>";
				}
				
				if(i==1){
					siv1="<div class='item'>";
				}
				
				if(i==2){
					siv1="<div class='item'>";
				}
				
				String siv2="<img src=down?id="+photoList.get(i).getWebPicture().getID()+" alt="+photoList.get(i).getPictureName()+">";
				String siv3="</div>";
				siv=siv+siv1+siv2+siv3;
			}
		}
		map.put("picture", siv);
		
		
		return JsonUtils.objectToJson(map);
	}

}
