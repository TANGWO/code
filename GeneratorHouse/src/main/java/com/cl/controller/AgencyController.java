 package com.cl.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cl.entity.Agency;
import com.cl.service.AgencyService;

/**
 * @author chenling
 * @date 2019/01/04
 */
 @RestController
public class AgencyController {
 
	 @Autowired
	 private AgencyService agencyService;
	 
	 @RequestMapping(value="/getAllAgency",method=RequestMethod.GET)
	 public String getAllAgency() {
		 return agencyService.findAgency();
	 }
	 
	 @RequestMapping(value="/findByMore",method=RequestMethod.GET)
	 public String findByMore() {
		 return agencyService.findByMore();
	 }
	 
	 @RequestMapping(value="/findAgencyByName",method=RequestMethod.GET)
	 public String findAgencyByName(String name) {
		 name = Strings.isBlank(name)?"chenling":name;
		 return agencyService.findAgencyByName(name);
	 }
	 
	 @RequestMapping(value="/findByPrimaryKey",method=RequestMethod.GET)
	 public String findByPrimaryKey(Integer id) {
		 id=id==null?1:id;
		 return agencyService.findByPrimaryKey(id);
	 }
	 
	 @RequestMapping(value="/findById",method=RequestMethod.GET)
	 public String findById(Integer id) {
		 id=id==null?1:id;
		 return agencyService.findById(id);
	 }
	 
	 @RequestMapping(value="/saveAgency",method=RequestMethod.GET)
	 public String saveAgency() {
		 Agency Agency = new Agency();
		 Agency.setAboutUs("通过以上sqlmapper.xml我们可以看出Example对象及它的内部对象属性在运行时被mybatis框架循环遍历解析成动态sql语句,与我们手写sql语句并无差别");
		 Agency.setAddress("中国.广州");
		 Agency.setEmail("chenlingys@live.com");
		 Agency.setMobile("18786705103");
		 Agency.setName("chenling");
		 Agency.setPhone("18786705103");
		 Agency.setWebSite("https://github.com/");
		 return agencyService.addAgencyByOneObject(Agency);
	 }
	 
	 
	 
}
