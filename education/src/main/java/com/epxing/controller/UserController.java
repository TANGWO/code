package com.epxing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epxing.entity.WebUser;
import com.epxing.service.UserService;
import com.epxing.util.JsonUtils;
import com.epxing.util.StringUtil;


@Controller
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	//通用控制器
	@RequestMapping("{page}")
	public String page(@PathVariable String page) {
		return page;
	}
	@RequestMapping("{page1}/{page2}")
	public String page(@PathVariable String page1,@PathVariable String page2) {
		return page1+"/"+page2;
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody()
	public String login(String account,String password,HttpServletRequest req,HttpServletResponse resp) {
		//NO_USER :用户不存在
		//BUG_USER ： 用户信息不完整
		//FULL_USER ：用户信息完整
		String flag ="NO_USER";
		WebUser user = userServiceImpl.login(account, StringUtil.encrypt(password));
		if(null == user){
			flag="NO_USER";
		}else{
			if(null == user.getRealName() || "" == user.getRealName()){
				req.getSession().setAttribute("user", user);
				flag="BUG_USER";
			}else{
				req.getSession().setAttribute("user", user);
				flag="FULL_USER";
			}
		}
		return flag;
	}
	
	
	/**
	 * 注册
	 * @param account
	 * @param password
	 */
	@RequestMapping("register")
	@ResponseBody
	public boolean register(String  account ,String password,HttpServletRequest req,HttpServletResponse resp){
		 boolean flag = false;
		 int value = userServiceImpl.register(account, StringUtil.encrypt(password));
		 if(value>0) {
			 WebUser user = new WebUser();
			 user.setAccount(account);
			 req.getSession().setAttribute("user",user);
			 flag = true;
		 }else{
			 flag = false;
		 }
		return flag;
	}
	
	/**
	 * 注册，继续完善信息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="consummateInfo",method=RequestMethod.POST)
	@ResponseBody
	public String consummateInfo(WebUser user,HttpServletRequest req,HttpServletResponse resp){
		
		//ERR_INFO      : 更新信息错误
		//NO_USER		：用户session过期
		//SUCCESS_INFO  ：更新信息成功
		
		String flag ="ERR_INFO";
		WebUser userInfo  = (WebUser) req.getSession().getAttribute("user");
		
		if(userInfo==null || userInfo.getAccount() == null){
			flag ="NO_USER";
		}else{
			user.setAccount(userInfo.getAccount());
			System.out.println(user.toString());
			int i = userServiceImpl.consummateInfo(user);
			flag=i>0?"SUCCESS_INFO":"ERR_INFO";
		}
		return  flag;
	}
	
	
	
	@RequestMapping(value="checkAccount", method=RequestMethod.POST)
	@ResponseBody
	public boolean checkAccount(String account){
		//false :表示不存在
		//true  :表示已存在
		boolean flag = userServiceImpl.checkAccount(account);
		return  flag;
	}
	
	
	/**
	 * 找回密码
	 */
	
	@RequestMapping(value="getpassword",method=RequestMethod.POST)
	@ResponseBody
	public String getPassword(String realName,String password,String idCard){
		//ERR_RE   :修改失败
		//SUCC_RE  :修改成功
		String flag="ERR_RE";
		int i;
		try {
			i = userServiceImpl.passwordRetrieval(realName,StringUtil.encrypt(password),idCard);
			if(i>0){
				flag="SUCC_RE";
			}else{
				flag="ERR_RE";
			}
		
		} catch (Exception e) {
			flag="ERR_RE";
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 手机找回密码
	 */
	@RequestMapping(value="getPasswordByPhone",method=RequestMethod.POST)
	@ResponseBody
	public String getPasswordByPhone(String account,String phone){
		//ERR_RE   :修改失败
		//SUCC_RE  :修改成功
		String info = userServiceImpl.getPasswordByPhone(account,phone);
		return info;
	}
	
	
	@RequestMapping(value="updateInfo",method=RequestMethod.POST)
	@ResponseBody
	public String updateInfo(WebUser user ){
		if(StringUtil.notNull(user.getPassword() )){
			user.setPassword(StringUtil.encrypt(user.getPassword()));
		}
		int updateInfo = userServiceImpl.updateInfo(user);
		if(updateInfo >0 ){
			return "SUCCESS";
		}
		return "FAILURE";
	}
	
	@RequestMapping(value="chenkPassword",method=RequestMethod.POST)
	@ResponseBody
	public String chenkPassword(String  account,String password){
		 boolean flag = userServiceImpl.checkPassword(account,StringUtil.encrypt(password));
		if(flag){
			return "SUCCESS";
		}
		return "FAILURE";
	}
	
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest req,HttpServletResponse resp){
		req.getSession().removeAttribute("user");
		return "index";
	}
	

}
