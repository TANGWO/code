package com.epxing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.epxing.entity.ContactUs;
import com.epxing.entity.Course;
import com.epxing.entity.CourseCatalogue;
import com.epxing.entity.MyCourse;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebUser;
import com.epxing.mapper.CourseMapper;
import com.epxing.mapper.InfoMapper;
import com.epxing.service.CourseService;
import com.epxing.util.JsonUtils;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Resource
	private  CourseMapper courseMapper ;
	
	@Resource
	private InfoMapper infoMapper;
	
	
	@Override
	public int getCount() {
		
		return courseMapper.getCount();
	}

	@Override
	public int getMyCourseCount() {
		
		return courseMapper.getMyCourseCount();
	}
	
	@Override
	public String getCourseList(Pagination<Course> p) {
		List<Course> list = courseMapper.getCourseList(p);
		HashMap<String, Object> map = new HashMap<String,Object>();
		ContactUs linkUs = infoMapper.getLinkUs();
		String div="";
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String div1="<div class='col-lg-4 col-md-4 col-sm-4 weui-pt10'>";
				String div2="<a href='onlineCourseTwo?code="+list.get(i).getCid()+"' class='weui-db '>";
				String div3="<div class='weui-p_r pro'>";
				String div4="<img src='down?id="+list.get(i).getCover().getID()+"' width='100%'>";
				String div5="<i></i> </div>";
				String div6=" <p class='weui-pt10'><span class=' weui-f16 weui-db weui-pb5'>课程名称："+list.get(i).getCourseName()+"</span>上传时间："+list.get(i).getUploadTime()+"</p>  </a> </div>";
				div=div+div1+div2+div3+div4+div5+div6;
			}
		}
		map.put("info", div);
		
		String s ="";
		if(null !=linkUs){
			String s0="<h4 class='weui-f20  weui-pb10'>联系我们</h4>";
			String s1="<p>电话："+linkUs.getPhone()+"</p>";
			String s2="<p>邮箱： "+linkUs.getEmail()+"</p>";
			String s3="<p>地址："+linkUs.getAddress()+"</p>";
			s =s+s0+s1+s2+s3;
		}
		map.put("link", s);
		
		return  JsonUtils.objectToJson(map);
	}

	@Override
	public String getCatalogueById(String id) {
		List<CourseCatalogue> list = courseMapper.getCatalogueById(id);
		HashMap<String, Object> map = new HashMap<String,Object>();
		String div="";
		String siv="";
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String div1="";
				if(i==0){
					div1="<span class='on' onclick='playVideo("+list.get(i).getCcid()+",this)'>"+list.get(i).getCcid()+" "+list.get(i).getCatalogueName()+"</span>";
				}else{
					div1="<span class=''  onclick='playVideo("+list.get(i).getCcid()+",this)' >"+list.get(i).getCcid()+" "+list.get(i).getCatalogueName()+"</span>";
				}
				div=div+div1;
			}
		    String siv1="<h2 class='weui-f20 weui-bb weui-pb15 weui-red01'>教程名称："+list.get(0).getOwnCourse().getCourseName()+"</h2>";
		    String siv2=" <h3>主讲人："+list.get(0).getOwnCourse().getSpeaker().getRealName()+"</h3>";
		    String siv3=" <p>课程介绍：<span class=.weui-c_9'>"+list.get(0).getOwnCourse().getCourseBriefIntroduction()+"</span></p>";
		    siv=siv+siv1+siv2+siv3;
		}
		map.put("CourseCatalogue", div);
		map.put("introduce", siv);
		return JsonUtils.objectToJson(map);
	}
	
	@Override
	public String pageGetInfo(Pagination<Course> p,String context) {
		Map <String,Object> map = new HashMap<String, Object>();
		String preButton="";
		if(p.isHasPrev()){
			 preButton ="onclick='getInfoByPage("+p.getPrevPage()+");'";
		  }else{
			  preButton="onclick='return false;'";
		  }
		String nextButton="";
		if(p.isHasNext()){
				nextButton="onclick='getInfoByPage("+p.getNextPage()+");'";
			}else{
				nextButton="onclick='return false;'";
		}
			
		String div="";
		String div1="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);'  onclick='getInfoByPage("+p.getFirstPage()+");'>首页</a></div>";
		String div2="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);'  "+preButton+">上一页</a></div>";
        String div3="<div class='form-group weui-pl15 weui-pr15'>第"+p.getPage()+"/"+p.getTotalPage()+"页</div>";
        String div4="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);' "+nextButton+">下一页</a></div>";
        String div5="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);' onclick='getInfoByPage("+p.getTotalPage()+");'>末页</a></div>";
        String div6="<div class='form-group weui-pl15 weui-pr15'><input type='number' id='inputPage' min='1' max='"+p.getTotalPage()+"' class='form-control' value='' style='width:40px;'></div>";
        String div7="<div class='form-group weui-pl15 weui-pr15' onclick='getInfoByInputPage();'>跳转到</div>";
        div=div1+div2+div3+div4+div5+div6+div7;
        map.put("pageLine", div);
        map.put("context", context);
		return JsonUtils.objectToJson(map);
	}
	

	@Override
	public String getCourseByUser(WebUser user,Pagination<MyCourse> p,String status) {
		Integer wuid = user.getWuid();
		Map <String,Object> map = new HashMap<String, Object>();
		List<MyCourse> myCourse = courseMapper.getMyCourseByUser(wuid,p,status);
		String div="";
		if(myCourse != null && myCourse.size()>0){
			for(int i=0;i<myCourse.size();i++){
				Integer courseId=myCourse.get(i).getCourse().getCid();
				String imgSrc=myCourse.get(i).getCourse().getCover().getFILE_PATH_();
				String context=myCourse.get(i).getCourse().getCourseBriefIntroduction();
				String title=myCourse.get(i).getCourse().getCourseName();
				String div1="<div class=' weui-bb_d weui-pb20  weui-pt20'>  <div class='row'> <div class='col-lg-4 col-md-4 col-sm-4'>";
				String div2=" <a href='#'><img src="+imgSrc+" width='100%'></a></div> <div class='col-lg-8 col-md-8 col-sm-8'>";
				String div3="<p class='weui-f20'><a href='onlineCourseTwo?code="+courseId+"'>"+title+"</a></p>";
				String div4="<p>"+context+"</p>";
				String div5="<a href='onlineCourseTwo?code="+courseId+"' class='btn btn-s'>去学习</a></div></div></div>";
				div=div1+div2+div3+div4+div5;
			}
		}
		map.put("info", div);
		
		ContactUs linkUs = infoMapper.getLinkUs();
		String s ="";
		if(null !=linkUs){
			String s0="<h4 class='weui-f20  weui-pb10'>联系我们</h4>";
			String s1="<p>电话："+linkUs.getPhone()+"</p>";
			String s2="<p>邮箱： "+linkUs.getEmail()+"</p>";
			String s3="<p>地址："+linkUs.getAddress()+"</p>";
			s =s+s0+s1+s2+s3;
		}
		map.put("link", s);
		
		String preButton="";
		if(p.isHasPrev()){
			 preButton ="onclick='getInfoByPage("+p.getPrevPage()+");'";
		  }else{
			  preButton="onclick='return false;'";
		  }
		String nextButton="";
		if(p.isHasNext()){
				nextButton="onclick='getInfoByPage("+p.getNextPage()+");'";
			}else{
				nextButton="onclick='return false;'";
		}
			
		String siv="";
		String siv1="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);'  onclick='getInfoByPage("+p.getFirstPage()+");'>首页</a></div>";
		String siv2="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);'  "+preButton+">上一页</a></div>";
        String siv3="<div class='form-group weui-pl15 weui-pr15'>第"+p.getPage()+"/"+p.getTotalPage()+"页</div>";
        String siv4="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);' "+nextButton+">下一页</a></div>";
        String siv5="<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);' onclick='getInfoByPage("+p.getTotalPage()+");'>末页</a></div>";
        String siv6="<div class='form-group weui-pl15 weui-pr15'><input type='number' id='inputPage' min='1' max='"+p.getTotalPage()+"' class='form-control' value='' style='width:40px;'></div>";
        String siv7="<div class='form-group weui-pl15 weui-pr15' onclick='getInfoByInputPage();'>跳转到</div>";
        siv=siv1+siv2+siv3+siv4+siv5+siv6+siv7;
        map.put("pageLine", siv);
		return  JsonUtils.objectToJson(map);
		
	}
	

	
}
