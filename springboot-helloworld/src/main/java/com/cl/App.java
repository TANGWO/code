package com.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	
	public static void main(String [] args ) {
		
		SpringApplication.run(App.class, args);
		
	}
	
	/**
	 * springBoot 的三种注解启动方式
	 * 1.在controller 中使用main方式，使用SpringApplication.run
	 * 2.使用注解，@ComponentScan扫描同级指定目录范围内的controller
	 * 3.@SpringBootApplication 代替@ComponentScan及@EnableAutoConfiguration
	 * 当前包和子包下的都可以扫描到
	 */
	

}
