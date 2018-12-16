package com.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MumberApp01 {
		public static void main(String[] args) {
			SpringApplication.run(MumberApp01.class, args);
	}

}
