package com.cl.house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan(basePackages={"com.cl.house.mapper"})
@EnableAsync
public class HouseApp {

	public static void main(String[] args) {
		SpringApplication.run(HouseApp.class, args);
	}
	
	
}
