package com.epxing.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 栏目信息
 * @author chenl
 */
@Setter
@Getter
@ToString
public class ColumnInformation {
	
	private String code; //栏目编号
	private String name; //栏目名称
	private ColumnInformation parentCode; //上级编号
}
