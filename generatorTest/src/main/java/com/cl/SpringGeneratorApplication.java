 package com.cl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenling
 * @date 2019/01/04
 */
 @SpringBootApplication
 //@MapperScan(basePackages= {"com.cl.mapper"})
 @MapperScan(basePackages="com.cl.mapper")
public class SpringGeneratorApplication {

	 public static void main(String[] args) {
		SpringApplication.run(SpringGeneratorApplication.class, args);
	}
}
