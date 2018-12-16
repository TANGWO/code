package com.epxing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epxing.util.VaildateCodeUtil;

/**
 * 验证码生成控制器
 * @author chenl
 *
 */
@Controller
public class RandomController {
	
	@RequestMapping(value="getCode",method=RequestMethod.GET)
	public void  getVaildateCode(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	    //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
		VaildateCodeUtil codeUtil = new VaildateCodeUtil();
		codeUtil.getCode(request, response);
	}
	
	@RequestMapping(value="checkVaildateCode",method=RequestMethod.POST)
	@ResponseBody
	public boolean checkVaildateCode(String identifying,HttpServletRequest request){
		boolean flag = false;
		String identifyings = (String) request.getSession().getAttribute("identifyings");
		if(identifyings.toLowerCase().equals(identifying.toLowerCase())){
			System.out.println("*******************验证码一致*************");
			flag =true;
		}else{
			flag = false;
		}
		return flag;
	}

}
