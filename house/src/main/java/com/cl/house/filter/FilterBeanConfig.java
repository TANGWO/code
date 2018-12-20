package com.cl.house.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterBeanConfig {
     
	/**
	 * 
	* @Title: 构造logFilter  
	* @Description: TODO(利用FilterRegistrationBean进行包装Filter )  
	* @param @return    参数  
	* @return FilterRegistrationBean    返回类型  
	* @throws
	 */
	@Bean
	public FilterRegistrationBean logFilter() {
		
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LogFilter());
		//拦截的url
		List<String> urlList = new ArrayList<>();
		urlList.add("*");
		filterRegistrationBean.setUrlPatterns(urlList);
		return filterRegistrationBean;
	}
	
}
