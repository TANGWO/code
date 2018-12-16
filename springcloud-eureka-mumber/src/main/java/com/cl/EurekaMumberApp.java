package com.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaMumberApp {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaMumberApp.class, args);
	}
}
