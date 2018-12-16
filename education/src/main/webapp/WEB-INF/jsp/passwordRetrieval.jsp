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
	<!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
	<title>密码找回</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">
</head>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<script type="text/javascript">

	/* 清除span信息 */
	function clearSpan(s){
		var spanId="#"+s;
		$(spanId).html("");
	}
	
	
	/* 验证密码的安全性 */
	function checkPassword(){
		var password = $("#password").val();
		var password1 = $("#password1").val();
		if(password == null || password == ""){
			$("#passwordSpan").html("密码不能为空！");
			return false;
		}
		var reg =  /^[0-9]+.?[0-9]*/;
		if(password.length <6 || reg.test(password) ){
			 $("#passwordSpan").html("密码过于简单，请重新输入密码，密码长度不能少于6位！"); 
			 return false;
		 }
		if(password1 !==null || password1 !==""){
			if(password1 !== password){}
			$("#password1Span").html("两次输入密码不一致！");
			return false;
		}
		return true;
		
	}
	
	/* 验证确认密码是否相同 */
	function checkPassword1(){
		var password = $("#password").val();
		var password1 = $("#password1").val();
		if(password1 == null || password1 ==""){
			$("#password1Span").html("确认密码不能为空！");
			return false;
		}
		if(password1 !==password){
			$("#password1Span").html("两次输入密码不一致！");
			return false;
		}
		return true;
	}
	
	/* 检查身份证件号是否合法 */
	function checkIdCard(){
		var reg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i; 
		var idCard = $("#idCard").val();
		
		if(idCard == null || idCard ==""){
			$("#idCardSpan").html("身份证件号码不能为空！");
			return false;
		}
		
		if( !reg.test(idCard)){
			$("#idCardSpan").html("请正确输入身份证件号码！");
			return false;
		}
		return true;
	}
	
   /* 提交信息 */
   function submitForm(){
	  var realName = $("#realName").val(); 
	  var idCard =  $("#idCard").val(); 
	  var password = $("#password").val();
	  var password1 = $("#password1").val();
	  
	  if(realName == null || realName ==""){
		  $("#realNameSpan").html("真实姓名不能为空！");
			return false;
	  }
	  
	  if(idCard == null || idCard ==""){
		  $("#idCardSpan").html("身份证件号不能为空！");
			return false;
	  }
	 var f1 = checkPassword();
	 var f2 = checkPassword1();
	 
	 if(f1 && f2){
		//ERR_RE   :修改失败
		//SUCC_RE  :修改成功
		 
		 $.ajax({
			   type: "POST",
			   url: "getPassword",
			   async:false,
			   data:{
				 'realName':realName,  
				  'idCard' :idCard,
				  'password':password
			   },
			   success: function(msg){
			     	if(msg=="ERR_RE"){
			     		alert("修改密码失败，请重试！");
			     		return false;
			     	}
			     	if(msg=="SUCC_RE"){
			     		window.location.href="index";	
					   
				   }
			   }
		});
	 }else{
		 alert("信息不完整，请检查信息后重试！");
		 return false;
	 }
   }
   
   /* 验证手机号码格式 */
   function checkPhone(){
		var reg =/^1[3|4|5|8][0-9]\d{4,8}$/;
		var phone = $("#phone").val();  
		 if(phone == null || phone ==""){
			  $("#phoneSpan").html("手机号码不能为空！");
				return false;
		  }
		 
		 if( !reg.test(phone)){
			 $("#phoneSpan").html("请正确输入手机号码！");
				return false;
		 }
		 return true;
   }

   
   /*手机找回密码  */
   function submitInfo(){
	   var phone = $("#phone").val();     
	   var account = $("#account").val();  
	   $.ajax({
		   type: "POST",
		   url: "getPasswordByPhone",
		   async:false,
		   data: {
			   'phone':phone,
			   'account':account
		   },
		   success: function(msg){
		     if(msg=="NO_ACCOUNT"){
		    	 alert("账号不存在，请核对后再次修改！");
		    	 return false;
		     }
		     if(msg=="ERROR_UPDATE"){
		    	 alert("修改失败，请联系管理员！");
		    	 return false;
		     }
		     if(msg=="ERROR_SENDSMS"){
		    	 alert("短信下发异常，请联系管理员！");
		    	 return false;
		     }
		     if(msg=="SUCCESS_SENDSMS"){
		    	 alert("修改成功，密码已通过短信已下发至您的手机【"+phone+"】,请注意查收！");
		    	 window.location.href="index";	
		     }
		     if(msg == "OUT_TIMES"){
		    	 alert("密码修改失败，已超出今日最大申请次数！");
		    	 return false;
		     }
		   }
		});
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
                    <a href="index.html" class="logo"><img src="/static/images/logo.png"> <span class="weui-v_m weui-pl10">国家级专业技术人员继续教育基地</span></a>
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
          <ul id="myTab" class="nav nav-tabs nav-tabs2 weui-f20">
                  <li class="active">
                      <a href="#home" data-toggle="tab">
                        <i class="i_h"></i>
                        <span>直接找回</span>
                      </a>
                  </li>
                  <li>
                      <a href="#ios" data-toggle="tab">
                         <i class="i_os"></i>
                         <span>手机找回</span>
                      </a>
                  </li>
          </ul>
          <div id="myTabContent" class="tab-content">
              <div class="tab-pane fade in active" id="home">
                    <div class="row weui-f16 weui-pt30 weui-pb30">
                     <div class="col-lg-6 col-md-6 col-sm-6 col-lg-push-3 col-md-push-3 col-sm-push-3">
                       <table width="100%" class="table1 weui-f20">
                           <tbody><tr>
                               <td class="weui-t_r" width="25%">真实姓名</td>
                               <td><input type="text" class="form-control form-control0" id="realName" name="realName"  value=""  placeholder="请输入真实姓名" onfocus="javascript:clearSpan('realNameSpan');"><span style="color: red;" id="realNameSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">身份证号</td>
                               <td><input type="text" class="form-control form-control0" id="idCard" name="idCard" value="" placeholder="请输入身份证件号" onfocus="javascript:clearSpan('idCardSpan');" onblur="javascript:checkIdCard();"  ><span style="color: red;" id="idCardSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">新密码</td>
                               <td><input type="password" class="form-control  form-control0" id="password" name="password" value="" placeholder="请输入新密码" onblur="javascript:checkPassword();" onfocus="javascript:clearSpan('passwordSpan');"><span style="color: red;" id="passwordSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">确认密码</td>
                               <td><input type="password" class="form-control form-control0" id="password1" name="password1" value="" placeholder="请确认新密码" onblur="javascript:checkPassword1();" onfocus="javascript:clearSpan('password1Span');"><span style="color: red;" id="password1Span"></span></td>
                           </tr>
                           
                       </tbody></table>
<!--                        <div class="weui-pt30 weui-pb30 weui-t_c"><input type="button" value="找回" class="btn btn-s weui-f20 btn-lg1" data-toggle="modal" data-target="#myModal" onclick="javascript:submitForm();"></div>
 -->                       <div class="weui-pt30 weui-pb30 weui-t_c"><input type="button" value="找回" class="btn btn-s weui-f20 btn-lg1"  onclick="javascript:submitForm();"></div>
                    </div>
                 </div>
              </div>
              <div class="tab-pane fade" id="ios">
                  <div class="row weui-f16 weui-pt30 weui-pb30">
                     <div class="col-lg-6 col-md-6 col-sm-6 col-lg-push-3 col-md-push-3 col-sm-push-3">
                       <table width="100%" class="table1 weui-f20">
                           <tbody><tr>
                               <td class="weui-t_r" width="25%">账户名称</td>
                               <td><input type="text" class="form-control form-control0" name="account" id="account" value="" placeholder="请输入登录账号"  onfocus="javascript:clearSpan('accountSpan');"><span style="color: red;" id="accountSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">手机号码</td>
                               <td><input type="text" class="form-control form-control0" name="phone" id="phone" value="" placeholder="请正确输入手机号码" onblur="javascript:checkPhone();" onfocus="javascript:clearSpan('phoneSpan');"><span style="color: red;" id="phoneSpan"></span></td>
                           </tr>
                           
                       </tbody></table>
                       <div class="weui-pt30 weui-pb30 weui-t_c"><input type="button" value="找回" class="btn btn-s weui-f20 btn-lg1" onclick="javascript:submitInfo();"></div>
                    </div>
                 </div>
             </div>
          </div>     
                    
                    
                    
                    
                 
                 
                 
    </div>
</div>


<div class="footer weui-t_c weui-lh30">
     相关链接：	<a href="#" class="weui-pl20 weui-pr20">南开大学</a>	|	<a href="#" class="weui-pl20 weui-pr20">人力资源社会保障部</a>	|	<a href="#" class="weui-pl20 weui-pr20">教师发展中心</a>	|	<a href="#" class="weui-pl20 weui-pr20">天津市人社局</a>	|	<a href="#" class="weui-pl20 weui-pr20">继续教育学院</a><br>
     地址：天津市南开区卫津路94号	<span class="weui-pl15">电话：022-23501215</span>		<span class="weui-pl15">邮编：041588</span>	<span class="weui-pl15">皖ICP备12003308号-1 Power by DedeCms</span><br>
     版权所有：南开大学继续教育学部
</div>




<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog weui-f20 weui-t_c">
        <div class="modal-content weui-pt10">
            <div class="modal-body">
                 <img src="/static/images/s_03.png"> <span class="weui-v_m weui-pl20 weui-pt10 weui-dnb">账户张三，密码已重置<span class="weui-red01 weui-f30">b568z9</span></span>
                 <p class="weui-pt15">为了您的账户安全，请立即登录系统重置</p>
            </div>
            <div class=" weui-pb20">
                  <input type="button" value="确定" class="btn btn-s weui-f20 weui-pl25 weui-pr25">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog weui-f20 weui-t_c">
        <div class="modal-content weui-pt10">
            <div class="modal-body">
                 <img src="/static/images/s_03.png"> <span class="weui-v_m weui-pl20 weui-pt10 weui-dnb">密码已发送至手机：<span class="weui-red01 weui-f30">12345678910</span></span>
            </div>
            <div class=" weui-pb20">
                  <input type="button" value="确定" class="btn btn-s weui-f20 weui-pl25 weui-pr25">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>