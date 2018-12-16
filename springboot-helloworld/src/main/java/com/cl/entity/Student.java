package com.cl.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class Student {
	
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	
	public static void main(String [] args) {
		Student stu = new Student();
		stu.setAge(18);
		stu.setName("陈领");
		stu.setId(0);
		stu.setSex("男");
		System.out.println(stu.toString());
		log.info(stu.toString());
		
	}
	
}
