 package com.cl.house.common.constants;

 /**
 * @author chenling
 * @date 2018/12/17
 */
public enum HouseUserType {
	
	SALE(1),
	
	BOOKMARK(2);
	
	public final Integer value;
	
	private HouseUserType(Integer value){
		this.value = value;
	}
	
	
}
