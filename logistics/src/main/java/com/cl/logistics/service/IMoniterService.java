package com.cl.logistics.service;

import java.util.List;

import com.cl.logistics.bean.CarCost;
import com.cl.logistics.bean.ContactsService;
import com.cl.logistics.bean.CustomerAmount;
import com.cl.logistics.bean.DriverAmount;
import com.cl.logistics.bean.GoodsBill;
import com.cl.logistics.bean.LineOverall;

public interface IMoniterService {

	List<GoodsBill> selectAllUnArrive();

	List<GoodsBill> selectAllUnTake();

	List<CustomerAmount> selectAllCusAcount();

	List<DriverAmount> selectAllDriAcount();

	List<ContactsService> printAllContactsService();

	List<LineOverall> printAllLineOverall();

	List<CarCost> printAllCarCost();

	CarCost selectByCode(String driverCode);

	ContactsService selectByGoodsBillCode(String goodsBillCode);


}
