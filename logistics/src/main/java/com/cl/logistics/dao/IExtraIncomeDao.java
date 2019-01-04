package com.cl.logistics.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.ExtraIncome;

public interface IExtraIncomeDao extends JpaRepository<ExtraIncome, Long>{

    public List<ExtraIncome> findByIncomeMonth(String incomeMonth);
	

}
