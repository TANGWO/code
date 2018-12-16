package com.epxing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.epxing.interceptor.ErrorPageInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private ErrorPageInterceptor errorPageInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//.addPathPatterns("/action/**", "/mine/**");默认所有
        registry.addInterceptor(errorPageInterceptor);
        super.addInterceptors(registry);
    }
}
