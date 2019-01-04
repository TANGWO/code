package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cl.logistics.bean.DriverAmount;

public interface IDriverAmountDao extends JpaRepository<DriverAmount, Long>{
	
//	@Modifying
//	@Query(value = "select driverCode,sum(carryFee) as carryFeeTotal ,sum(addCarriage) as addCarriageTotal ,count(driverCode) as total from driverclear group by driverCode order by total DESC")
//	List<DriverAmount> find();

}
