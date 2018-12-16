<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8" />
	<title>注册</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">

</head>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<script type="text/javascript">

	var s1=false;
	var s2=false;
	var s3=false;
	var s4=false;
	
	
	/* 清楚span信息 */
	function clearSpan(s){
		var spanId="#"+s;
		$(spanId).html("");
	}
	
	/*获取验证码  */
	function getNewImg() {
		document.getElementById("img").src="getCode?time"+new Date();
	}
	  
	 /*检查用户是否已经被注册 ，以及用户名长度6~18*/
	 function checkAccount(){
		 var account  = $("#account").val(); 
			var reg =/^[a-zA-Z0-9]+$/;
			var f1=false;
			var f2=false;
			var f3=false;
			if(account==null || account==""){
				$("#accountSpan").html("用户名不能为空");
				return false;
			}else{
				f1=true;
			}
			 if(account.length<6 || account.length>18){
				$("#accountSpan").html("用户名长度需为6~18个字符！");
				return false;
			}else{
				f2=true;
			} 
			
			 if(!reg.test(account)){
				$("#accountSpan").html("用户名只能是英文字符或数字组成！");
				return false;
			} else{
				f3=true;
			}
		
			if(f1 && f2 && f3){
				 $.ajax({
					   type: "POST",
					   url: "checkAccount",
					   async:false,
					   data: {'account':account},
					   success: function(msg){
					     if(msg){
					    	$("#accountSpan").html("用户名已存在，请更换其他用户名！"); 
					    	return false;
					     }else{
					    	 s1 =true;
					     }
					   }
					});
			}
	 }
	 
	  
	 /* 验证密码的安全强度 */
	 function checkPassword(){
		 var password  = $("#password").val(); 
		 var reg =  /^[0-9]+.?[0-9]*/;
		 var f4 =false;
		 var f5 =false;
		 if(password == null || password==""){
			 $("#passwordSpan").html("密码不能为空！"); 
			 f4 =false;
			 return false;
		 }else{
			 f4 =true;
		 }
		 
		 if(password.length <6 || reg.test(password) ){
			 $("#passwordSpan").html("密码过于简单，请重新输入密码，密码长度不能少于6位！"); 
			 f5 =false;
			 return false;
		 }else{
			 f5=true;
		 }
		 
		 
		 if( f4 && f5){
		   var password1 = $("#password1").val(); 
			 if(password1 !==null || password1 !==""){
			   clearSpan("password1Span");
		   	   var flags = checkPassword1();
				   if(flags){
					   s2 =true;
					}else{
					   s2 =false;
					}
			 }else{
				 s2 =true;
			 }
		 }else{
			 s2=false;
		 }
	 }
	 
	 /* 验证两次输入密码是否一致 */
	 function checkPassword1(){
		var password1 = $("#password1").val(); 
		var password  = $("#password").val(); 
		var f6 =false;
		var f7 =false;
		if(password1 == null || password1 ==""){
			 $("#password1Span").html("密码验证不能为空！"); 
			 f6 =false;
			 return false;
		}else{
			f6 =true;
		}
		if(password1 !== password){
			 $("#password1Span").html("两次输入密码不一致！"); 
			 f7 =false;
			 return false;
		}else{
			 f7 =true;
		}
		
		if(f6 && f7){
			s3 =true;
		}else{
			s3=false;
		}
		return true;
	 }
	 
	 /* 验证验证码 */
	 function checkidentifying(){
		 var identifying  = $("#identifying").val(); 
		 var f8 =false;
		 var f9 =false;
		 if(identifying == null || identifying ==""){
			 $("#identifyingSpan").html("验证码不能为空！"); 
			 f8 =false;
			 return false;
		 }else{
			 f8 =true; 
		 }
		 
		 $.ajax({
			   type: "POST",
			   url: "checkVaildateCode",
			   data: {'identifying':identifying},
			   async:false,
			   success: function(msg){
				console.log("msg"+msg);
			    if(msg){
			    	f9 =true;
			    }else{
			    	 $("#identifyingSpan").html("验证码不匹配！"); 
					 f9 =false;
					 return false;
			    }
			   }
			});
		 console.log("f9"+f9);
		 if(f8 && f9){
			 s4=true;
		 }else{
			 s4 =false;
		 }
		 
	 }
	 
	 
	 /* 提交表单 */
	 function submitForm (){
		var account = $("#account").val(); 
		var password = $("#password").val(); 
		checkAccount();
		checkPassword();
		checkPassword1();
		checkidentifying();
		console.log("s1"+s1);
		console.log("s2"+s2);
		console.log("s3"+s3);
		console.log("s4"+s4);
		if(s1 && s2 && s3 && s4){
			$.ajax({
				   type: "POST",
				   url: "register",
				   async:false,
				   data: {
					'account' :account,
					'password':password
				   },
				   success: function(msg){
				     if(msg){
				    	 window.location.href="registerTwo";				    	 
				     }else{
				    	 alert("注册失败，请联系管理员！");
				    	 return false;
				     }
				   }
				});
		    }else{
		    	alert("请完善信息后再提交！");
		    }
     	 }
	
</script>
<body>
<div class="top">
     <div class="container">
          <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 weui-c_9"><i class="glyphicon glyphicon-home"></i> 南开主页</div>
                <div class="col-lg-6 col-md-6 col-sm-6 weui-t_r"><a href="index#tips" class="weui-c_9">登录</a> | <a href="registerOne" class="weui-c_9">注册</a></div>
           </div>
     </div>
</div>
<div class="header weui-pt10 weui-pb10">
     <div class="container">
           <div class="row">
                <div class="col-lg-9 col-md-9 col-sm-9">
                    <a href="index" class="logo"><img src="/static/images/logo.png"> <span class="weui-v_m weui-pl10">国家级专业技术人员继续教育基地</span></a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 weui-pt15">
                    <div class="input-group">
                       <input type="text" class="form-control" placeholder="请输入关键字...">
                       <span class="input-group-btn">
                          <button class="btn btn-s" type="button"><i class="glyphicon glyphicon-search"></i></button>
                       </span>
                    </div>
                </div>
           </div>
     </div>
</div>

<div class="nav01 weui-f16">
     <div class="container">
           <ul class="clearfix">
               <li class="weui-fl"><a href="index" class="on">首页</a></li>
               <li class="weui-fl"><a href="introduce">基地介绍</a></li>
               <li class="weui-fl"><a href="noticeOne">通知公告</a></li>
               <li class="weui-fl"><a href="dynamicsInformation">资讯动态</a></li>
               <li class="weui-fl"><a href="trainingProjects">培训项目</a></li>
               <li class="weui-fl"><a href="onlineCourseOne">在线课程</a></li>
           </ul>
     </div>
</div>

<div class="container">
     <div class="find">
          <ul id="myTab" class="nav nav-tabs nav-tabs2 nav-tabs3 weui-f20">
                  <li class="active">
                      <a href="javascripot:;" >
                        <i class="i_z"></i>
                        <span>账号信息</span>
                      </a>
                  </li>
                  <li>
                      <a href="javascripot:;">
                         <i class="i_g"></i>
                         <span>个人信息</span>
                      </a>
                  </li>
                  <li>
                      <a href="javascripot:;" >
                         <i class="i_c"></i>
                         <span>注册成功</span>
                      </a>
                  </li>
          </ul>
          <div id="myTabContent" class="tab-content">
              <div class="tab-pane fade in active" id="home">
                    <div class="row weui-f16 weui-pt30 weui-pb30">
                     <div class="col-lg-6 col-md-6 col-sm-6 col-lg-push-3 col-md-push-3 col-sm-push-3">
                       <table width="100%" class="table1 weui-f20">
                           <tbody><tr>
                               <td class="weui-t_r" width="25%">用户名</td>
                               <td><input type="text" class="form-control form-control0" name="account" id="account" value="" onblur="javascript:checkAccount(this);" onfocus="javascript:clearSpan('accountSpan');"> <span style="color: red;" id="accountSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">密码</td>
                               <td><input type="password" class="form-control form-control0" name="password" id="password" value="" onblur="javascript:checkPassword(this);" onfocus="javascript:clearSpan('passwordSpan');"> <span style="color: red;" id="passwordSpan"></span></td>
                           		
                           </tr>
                           <tr>
                               <td class="weui-t_r">确认密码</td>
                               <td><input type="password" class="form-control form-control0" name="password1" id="password1" value="" onblur="javascript:checkPassword1(this);" onfocus="javascript:clearSpan('password1Span');"> <span style="color: red;" id="password1Span"></span></td>
                           </tr>
                           
                           <tr >
                               <td class="weui-t_r">验证码</td>
                               <td width="">
                                   <div class="form-inline">
                                       <div class="form-group">
                                         <input type="text" class="form-control form-control0" style="width:140px;" name="identifying" id="identifying" value="" onblur="javascript:checkidentifying(this);" onfocus="javascript:clearSpan('identifyingSpan');"> <span style="color: red;" id="identifyingSpan"></span>
                                       </div>
                                         <img src="getCode" id="img" width="109" height="30" onclick="javascript:getNewImg()" /> 
                                         <a href="javascript:void(0);"  onclick="javascript:getNewImg();">换一张</a>
                                   </div>
                               </td>
                           </tr>
                       </tbody></table>
                       <!--  div class="weui-pt30 weui-pb30 weui-t_c"><a href="registerTwo"><input type="button" value="下一步" class="btn btn-s weui-f20 btn-lg1"></a></div -->
                       <div class="weui-pt30 weui-pb30 weui-t_c"><a href="javascript:void(0);"><input type="button" value="下一步" class="btn btn-s weui-f20 btn-lg1" onclick="javascript:submitForm();"></a></div>
                    </div>
                 </div>
              </div>
              
          </div>     
                    
    </div>
</div>

<div class="footer weui-t_c weui-lh30" style="position:static!important;">
     相关链接：	<a href="#" class="weui-pl20 weui-pr20">南开大学</a>	|	<a href="#" class="weui-pl20 weui-pr20">人力资源社会保障部</a>	|	<a href="#" class="weui-pl20 weui-pr20">教师发展中心</a>	|	<a href="#" class="weui-pl20 weui-pr20">天津市人社局</a>	|	<a href="#" class="weui-pl20 weui-pr20">继续教育学院</a><br>
     地址：天津市南开区卫津路94号	<span class="weui-pl15">电话：022-23501215</span>		<span class="weui-pl15">邮编：041588</span>	<span class="weui-pl15">皖ICP备12003308号-1 Power by DedeCms</span><br>
     版权所有：南开大学继续教育学部
</div>

</body>
</html>