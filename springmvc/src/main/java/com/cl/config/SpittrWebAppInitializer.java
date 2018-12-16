package com.cl.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * ����SpringMVCDispatcherServlet
 * @author chenl
 *
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    //���ظ�������Ϣspring���� 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}
    //spring����������Ϣ
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}
	//springmvc ����urlӳ�����������
	@Override
	protected String[] getServletMappings() {
		return new String []  {"/"};
	}

}
