package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.CarCost;

public interface ICarCostDao extends JpaRepository<CarCost, Long>{

	public CarCost findByDriverCode(String driverCode);


}
