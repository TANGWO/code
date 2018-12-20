package com.cl.house.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix="spring.httpclient")
@Data
public class HttpClientProperties {
	
	private String agent ="agent";

	/**
	 * ConnectionRequestTimeout
	  * 从连接池中获取连接的超时时间
	 */
	private Integer ConnectionRequestTimeout =1000;
	
	/**
	 *connectTimeOut
	  * 与服务器连接超时时间：httpclient会创建一个异步线程用以创建socket连接，
	  * 此处设置该socket的连接超时时间
	 */
	private Integer connectTimeOut =100000;
	 
	/**
	 * scoketTimeOut
	 * socket读数据超时时间：从服务器获取响应数据的超时时间. 等待响应超时（读取数据超时）
	 */
	private Integer scoketTimeOut =10000;
	
	/**
	 * maxConnPerRout
          * 分配给同一个route(路由)最大的并发连接数。
     * route：运行环境机器 到 目标机器的一条线路。
          * 举例来说，我们使用HttpClient的实现来分别请求 www.baidu.com
          *  的资源和 www.bing.com 的资源那么他就会产生两个route。
     */
	private Integer maxConnPerRout=10;
	
	/**
	 * maxConnTotal
	   * 连接池中最大连接数
	 */
	private Integer maxConnTotal =50;
}
