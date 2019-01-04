 package com.cl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.config.ParsingUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cl.dao.NewsDao;
import com.cl.entity.News;
import com.cl.service.NewService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author chenling
 * @date 2019/01/02
 */
 @Service
public class NewServiceImpl implements NewService {
  
	 
	@Autowired
    private NewsDao newsRepository;	 
	 
	public  String saveNews(News news) {
		News save = newsRepository.save(news);
	     String jsonString = JSONObject.toJSONString(save);
		return jsonString;
	}

	@Override
	public String updateById(Long id,News news) {
		News save = newsRepository.save(news);
		String jsonString = JSONObject.toJSONString(save);
		return jsonString;
	}

	/* (non-Javadoc)
	 * @see com.cl.service.NewService#updateByTitle(com.cl.entity.News)
	 */
	@Override
	public String updateByTitle(News news) {
		List<News> list = newsRepository.findAllNews(news.getTitle());
		list.forEach(newss->{
			newss.setContext(news.getContext());
		});
		List<News> list2 = newsRepository.saveAll(list);
		String jsonString = JSONObject.toJSONString(list2);
		return jsonString;
	}

	@Override
	public String updateByIdForOne(Long id, News news) {
		Optional<News> findById = newsRepository.findById(id);
		News news3 = findById.get();
		Example<News> example = Example.of(news3);
		Optional<News> findOne = newsRepository.findOne(example);
		News news2 = findOne.get();
		news2.setTitle("通过findOne方法修改参数,查询出来不进行保存操作，修改数据是无效的 ！");
		News save = newsRepository.save(news2);
		return JSONObject.toJSONString(news2);
	}

	@Override
	@Transactional
	public String updateByValue(Long id, String context) {
		int value = newsRepository.updateByValue(id,context);
		 return value>0?"success":"failure";
	}

	@Override
	public String deleteNews(Long[] id) {
		List<News> list = new ArrayList<News>();
		Lists.newArrayList(id).forEach(value->{
			News news = new News();
			news.setId(value);
			list.add(news);
		});
		newsRepository.deleteInBatch(list);
		return "success";
	}
	
	
}
