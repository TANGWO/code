 package com.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cl.service.impl.NewServiceImpl;

/**
 * @author chenling
 * @date 2019/01/02
 */
 @SpringBootApplication
public class JpaApplication {
	 
	 public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
}
