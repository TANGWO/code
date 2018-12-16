package com.cl.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载SpringMVCDispatcherServlet
 * @author chenl
 *
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    //加载根配置信息spring核心 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}
    //spring加载配置信息
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}
	//springmvc 拦截url映射的所有请求
	@Override
	protected String[] getServletMappings() {
		return new String []  {"/"};
	}

}
