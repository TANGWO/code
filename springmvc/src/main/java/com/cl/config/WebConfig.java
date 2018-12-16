package com.cl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc��������Ϣ
 * @author chenl
 *
 */
@Configuration  //��ͬ�������ļ��ϵ�bean
@EnableWebMvc   //����springmvc�Ĺ���
@ComponentScan(basePackages="com.cl.controller")  //ɨ���
public class WebConfig extends WebMvcConfigurerAdapter{
	
	//������ͼת����
	// ����SpringMVC��ͼ������
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
        //������JSPҳ����ͨ��${}����beans
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}

	
}
