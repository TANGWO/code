 package com.cl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chenling
 * @date 2019/01/03
 */
 @Configuration
 @EnableSwagger2
public class Swagger2 {
	 
	 @Bean
	 public Docket createRestApi() {
		 
		 return new Docket(DocumentationType.SWAGGER_2)
				     .apiInfo(apiInfo())
				     .select()
				     .apis(RequestHandlerSelectors.basePackage("com.cl.controller"))
				     .paths(PathSelectors.any())
				     .build();
	 }

	private ApiInfo apiInfo() {
		
		 return new ApiInfoBuilder()
				 .title("我的API")
				 .contact(new Contact("chenling lee", "暂未提供网站信息", ""))
	        		.version("1.0")
	        		.description("API 描述：第一次碰到这玩意，不知道是啥，甚至不知道则能够干啥？后来简单的了解了一下，发现这东西"
	        				+ "额能够做很多事情，我们以前要求的统计Api,接口信息，不全都是这个吗？那为什么还要自己动手去做呢？开源的世界很强大，所以你不要重复造轮子！")
	        		.build();
	}
}
