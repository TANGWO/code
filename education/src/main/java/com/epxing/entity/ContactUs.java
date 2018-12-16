package com.epxing.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 联系我们
 * @author chenl
 *
 */
@Setter
@Getter
@ToString
public class ContactUs {

	private Integer cuid;  
	private String phone;//联系电话
	private String email;//联系邮箱
	private String address;//联系地址
}
