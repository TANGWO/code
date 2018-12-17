package com.cl.house.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties({HttpClientProperties.class})
public class HttpClientAutoConfig {
	
	private final HttpClientProperties properties;

	public HttpClientAutoConfig(HttpClientProperties properties) {
		//super();
		this.properties=properties;
	}
	
	
	@Bean
	@ConditionalOnMissingBean(HttpClient.class)
	public HttpClient httpClient() {
		
		//构建requestConfig
		RequestConfig requestConfig =RequestConfig .custom()
				.setConnectTimeout(properties.getConnectTimeOut())
				.setSocketTimeout(properties.getScoketTimeOut())
				.build();
		//构建httpClient
		HttpClient httpClient = HttpClientBuilder
				.create()
				.setDefaultRequestConfig(requestConfig)
				.setUserAgent(properties.getAgent())
				.setMaxConnPerRoute(properties.getMaxConnPerRout())
				.setMaxConnTotal(properties.getMaxConnTotal())
				//防止连接失败，进行重试
				.setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
		
	   return httpClient;
		
	}

}
