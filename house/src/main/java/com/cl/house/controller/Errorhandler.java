 package com.cl.house.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cl.house.filter.LogFilter;

/**
 * @author chenling
 * @date 2018/12/17
 */
@ControllerAdvice
public class Errorhandler {
	
	private static final Logger logger = LogManager.getLogger(LogFilter.class);

	@ExceptionHandler(value={Exception.class,RuntimeException.class})
	public String error500(HttpServletRequest request,Exception e){
		logger.error(e.getMessage(),e);
		logger.error(request.getRequestURL() + " encounter 500");
		return "error/500";
	}
	
}
