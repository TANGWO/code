package com.epxing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.epxing.mapper"})
public class EducationApp {
	
	public static void main(String[] args) {
		SpringApplication.run(EducationApp.class, args);
	}
}	
