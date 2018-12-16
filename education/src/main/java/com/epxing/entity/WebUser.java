package com.epxing.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * 教育网站用户信息
 * @author chenl
 *
 */
@Setter
@Getter
@ToString
public class WebUser {
	private String account;
	private Integer wuid;
	private String password;
	private String realName;		//真实姓名
	private String phone;			//电话号码
	private String idCard;  		//身份证
	private String workUnit;        //工作单位
	private String politicalStatus;   //政治面貌
	private String technicalPosition;//专业技术职务
	private String educationalBackground;//学历

}
