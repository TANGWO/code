package com.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 第二种启动方式
 * 扫描范围，同级包和子包
 * @author chenl
 *
 */
@SpringBootApplication
public class App {
		   
		public static void main(String[] args) {
			
			SpringApplication.run(App.class, args);
			
		}
	
}
