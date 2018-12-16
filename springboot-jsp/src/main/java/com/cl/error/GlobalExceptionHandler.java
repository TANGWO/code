package com.cl.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获
 * @author chenl
 *
 */

@ControllerAdvice(basePackages="com.cl.controller")
public class GlobalExceptionHandler {
	/**
	 * 拦截异常类型
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String,Object> returnResult(){
		Map<String,Object> returnResultMap = new HashMap<String,Object>();
		returnResultMap.put("errorCode", "500");
		returnResultMap.put("errorMsg", "系统错误");
		return returnResultMap;
	}
	
}
