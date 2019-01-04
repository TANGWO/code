 package com.cl.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.entity.News;
import com.cl.service.NewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author chenling
 * @date 2019/01/02
 */
@Controller
@Api(value = "JPA练习 controller")
public class NewsController {

	@Autowired
	private NewService newServiceImpl;
	
	@RequestMapping(value="/saveNews",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="保存信息",notes="基于整个对象保存，保存之前先新建对象！")
	public String saveNews() {
		News news = new News();
		news.setAutor("chenling");
		news.setContext("基于接口的审计 如果你不想用注解来做审计的话，那么你可以实现Auditable接口。他暴露了审计属性的get/set方法。 如果你不想实现接口，那么你可以继承AbstractAuditable，通常来说，注解方式时更加方便的");
		news.setCreatTime(new Date());
		news.setIsPublish(1);
		news.setSource("中国新闻网");
		news.setTitle("spring-data-jpa 学习");
		return newServiceImpl.saveNews(news);
	}
	
	@RequestMapping(value="/updateNews",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="修改信息",notes="基于主键修改单个对象保存，修改之前先新建对象！")
	public String updateById() {
		Long id = 11l;
		News news = new News();
		news.setAutor("yaoxia");
		news.setContext("然后偶然的一次机会我才发现，jpa自动生成的save方法会先执行查询方法，如果无就insert into，如果有就 update，所以其实想执行update，直接执行");
		news.setCreatTime(new Date());
		news.setIsPublish(0);
		news.setSource("中国新闻网");
		news.setTitle("spring-data-jpa 学习2");
		news.setId(id);
		String updateById = newServiceImpl.updateById(id,news);
		return updateById;
	}
	
	@RequestMapping(value="/updateByIdForOne",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="修改信息",notes="基于主键修改整个对象保存，修改之前先新建对象！，使用findOne执行查询")
	public String updateByIdForOne() {
		Long id = 2l;
		News news = new News();
		news.setAutor("chenling");
		news.setContext("单独更新某个数据无法使用对象直接保存，有没有其他好的方法？");
		news.setSource("中国新闻网");
		news.setTitle("spring-data-jpa 学习");
		news.setId(id);
		String updateById = newServiceImpl.updateByIdForOne(id,news);
		return updateById;
	}
	
	@RequestMapping(value="/updateNewsByTitle",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="修改信息",notes="基于标题修改匹配对象保存，使用java8 lamda表达式执行")
	public String updateByTitle() {
		News news = new News();
		news.setContext("单独更新某个数据无法使用对象直接保存，有没有其他好的方法？");
		news.setTitle("spring-data-jpa 学习");
		String updateById = newServiceImpl.updateByTitle(news);
		return updateById;
	}
	
	@RequestMapping(value="/updateByValue",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="修改信息",notes="使用原生SQL修改数据")
	public String updateByValue() {
		Long id=22l;
		String context="使用原生SQL修改数据。。。。";
		return newServiceImpl.updateByValue(id,context);
	}
	
	@RequestMapping(value="/deleteByBacth",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="删除信息",notes="批量删除信息")
	public String deleteNews() {
		Long [] id= {22l,33l,44l};
		return newServiceImpl.deleteNews(id);
	}
	
	
}
