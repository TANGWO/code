package com.cl.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cl.order.service.OrderService;

@RestController
public class OrderController {
	@Autowired	
	private OrderService orderService;
	
	@RequestMapping(value="/getAllUser")
	public List<String>  getAllUser(){
		return orderService.getAll();
	}
	
	
}
