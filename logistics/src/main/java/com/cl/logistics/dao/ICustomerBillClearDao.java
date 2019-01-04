package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cl.logistics.bean.CustomerBillClear;

public interface ICustomerBillClearDao extends JpaRepository<CustomerBillClear, Long> {
	
	
	public CustomerBillClear findByGoodsBillCode(String billCode);



}