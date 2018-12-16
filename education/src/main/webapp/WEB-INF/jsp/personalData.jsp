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
	<title>个人资料</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">
<style type="text/css">
	input::-webkit-outer-spin-button,input::-webkit-inner-spin-button{
	   -webkit-appearance: none !important;
	}
	input[type="number"]{-moz-appearance:textfield;}
	
	.warn {border:1px solid #F02020;outline:none;}
</style>
</head>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<script type="text/javascript">
	function save(v){
		var form='#'+v;
		var cardReg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i; 
		var phoneReg=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
		 var d = {};
		    var t = $(form).serializeArray();
		    $.each(t, function() {
		    	 var value = this.value;
		    	 if(!value || value=="" || value=="   "){
		    		 $("input[name="+this.name+"]").addClass("warn");
						alert('该选项为必填项，请完善后再保存！');return;
				  }
		    	 if(this.name=="idCard"){
						if(!cardReg.test(value)){
							alert("身份证号码不符合规则，请修改后再保存修改！");return;
						}
					}
				if(this.name=="phone"){
					if(!phoneReg.test(value)){
						alert("电话号码不符合规则，请修改后再保存修改！");return;
					}
				}
		      d[this.name] = this.value;
		    });
		    
		    $.ajax({
				   type: "POST",
				   url: "updateInfo",
				   data:d,
				   async:false,
				   success: function(msg){
					   if(msg){
						   if(msg=="SUCCESS"){
							   alert("修改信息成功！");
						   }else{
							   alert("修改信息失败！");
						   }
					   }else{
						   alert("修改信息成功！");
					   }
				   }
			});
	}
	
	function savepassword(v){
	    var oldPassword = $('#oldPassword').val();
	    var newPassword = $('#newPassword').val();
	    var sureNewPassword = $('#sureNewPassword').val();
	    var wid = $('#wid').val();
	    if(!checkPassword(wid,oldPassword)){
	    	$("#oldPassword").addClass("warn");
	    	alert("密码不正确，请重新输入！");
	    	return;
	    }
	    if(!checkNewPassword(newPassword,sureNewPassword)){
	    	return;
	    }
	    $.ajax({
			   type: "POST",
			   url: "updateInfo",
			   data:{
				   'account':wid,
				   'password':sureNewPassword
			   },
			   async:false,
			   success: function(msg){
				   if(msg){
					   if(msg=="SUCCESS"){
						  alert('密码修改成功,请重新登录！');
							window.location.href='logout';
					   }else{
						   alert('密码修改失败！');
					   }
				   }else{
					   alert('密码修改失败！');
				   }
			   }
		});
	}
	
	
	function checkPassword(wid,oldPassword){
		var flag=false;
		$.ajax({
			   type: "POST",
			   url: "chenkPassword",
			   data:{
				   'account':wid,
				   'password':oldPassword
			   },
			   async:false,
			   success: function(msg){
				   if(msg){
					   if(msg=="SUCCESS"){
						   flag=true;
					   }else{
						   flag= false;
					   }
				   }else{
					   flag= false;
				   }
			   }
		});
		
		return flag;
	}
	
	function checkNewPassword(newPassword,sureNewPassword){
		clearCss($('#newPassword'));
		clearCss($('#sureNewPassword'));
		if(!newPassword || newPassword==null || newPassword =='' || newPassword==" " ){
			 $("#newPassword").addClass("warn");
			 alert("密码不能为空！"); return false;
		}
		if(!sureNewPassword || sureNewPassword==null || sureNewPassword=='' || sureNewPassword==" " ){
			 $("#sureNewPassword").addClass("warn");
			alert("确认密码不能为空！"); return false;
		}
		if(newPassword.length<6 || sureNewPassword<6 ){
			alert("密码长度不能低于6个字符！"); return false;
		}
		
		if(newPassword !==sureNewPassword ){
			alert("前后密码不一致"); return false;
		}
		return true;
	}
	
	function clearCss(v){
		$(v).removeClass("warn");
		$('#'+v.name+'Span').html('');
	}
	
	function inputBlur(v){
		if(v.name=='oldPasswordN'){
			var flag = checkPassword($('#wid').val(),v.value);
			console.log(flag);
			if(!flag){
				 $(v).addClass("warn");
				 $('#oldPasswordNSpan').html('密码错误，请重新输入！');
				  return;
			}
		}
		if(v.name=='newPasswordN'){
			if(v.value.length<0 || v.value== " " || v.value==null || v.value=='' ){
				 $(v).addClass("warn");
				 $('#newPasswordNSpan').html('密码不能为空或者空字符！');
				  return;
			}
		}
		if(v.name=='sureNewPasswordN'){
			if(v.value !==$('#newPassword').val()){
				 $(v).addClass("warn");
				 $('#sureNewPasswordNSpan').html('前后密码不一致!');
				  return;
			}
		}
		
	}
	
	
</script>
<body>
<div class="top">
     <div class="container">
          <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 "><i class="glyphicon glyphicon-home"></i> 南开主页</div>
                <div class="col-lg-6 col-md-6 col-sm-6 weui-t_r">${user.realName}   <a href="logout">退出</a></div>
           </div>
     </div>
</div>
<div class="header weui-pt10 weui-pb10">
     <div class="container">
           <div class="row">
                <div class="col-lg-9 col-md-9 col-sm-9">
                    <a href="#" class="logo"><img src="/static/images/logo.png"> <span class="weui-v_m weui-pl10">国家级专业技术人员继续教育基地</span></a>
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
               <li class="weui-fl"><a href="myCourse">我的课程</a></li>
               <li class="weui-fl"><a href="dataDownload">资料下载</a></li>
               <li class="weui-fl"><a href="personalData" class="on">个人中心</a></li>
           </ul>
     </div>
</div>

<div class="container weui-pt20">
     <div class="weui-bod xxk">
          <ul id="myTab" class="nav nav-tabs nav-tabs1">
             <li class="active"><a href="#home" data-toggle="tab">个人资料 </a></li>
             <li><a href="#ios" data-toggle="tab">修改密码</a></li>
          </ul>
          <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                 <div class="row weui-f16 weui-pt30 weui-pb30">
                     <div class="col-lg-6 col-md-6 col-sm-6 col-lg-push-3 col-md-push-3 col-sm-push-3">
                      <form  id="baseform">
                       <table width="100%" class="table1">
                           <tr>
                               <td class="weui-t_r" width="25%">真实姓名</td>
                               <td><input type="text" class="form-control form-control0"  name="realName" value="${user.realName}"></td>
                               <td><input type="hidden" class="form-control form-control0" name="wuid" value="${user.account}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">手机号</td>
                               <td><input type="text" class="form-control form-control0" name="phone" value="${user.phone}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">身份证号</td>
                               <td><input type="text" class="form-control form-control0" name="idCard" value="${user.idCard}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">工作单位</td>
                               <td><input type="text" class="form-control form-control0" name="workUnit" value="${user.workUnit}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">政治面貌</td>
                               <td><input type="text" class="form-control form-control0" name="politicalStatus" value="${user.politicalStatus}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">专业技术职务</td>
                               <td><input type="text" class="form-control form-control0" name="technicalPosition" value="${user.technicalPosition}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">学历信息</td>
                               <td><input type="text" class="form-control form-control0" name="educationalBackground" value="${user.educationalBackground}"></td>
                           </tr>
                       </table>
                       </form>
                       <div class="weui-pt30 weui-pb30 weui-t_c"><input type="button" onclick="javascript:save('baseform');" value="保存" class="btn btn-s weui-f16 weui-pl30 weui-pr30"></div>
                    </div>
                 </div>
            </div>
            <div class="tab-pane fade" id="ios">
                 <div class="row weui-f16 weui-pt30 weui-pb30">
                     <div class="col-lg-6 col-md-6 col-sm-6 col-lg-push-3 col-md-push-3 col-sm-push-3 col-xs-push-3">
                       <form id="passwordform">
                       <table width="100%" class="table1">
                           <tr>
                               <td class="weui-t_r" width="25%">旧密码</td>
                               <td><input type="password" class="form-control form-control0" id="oldPassword" name="oldPasswordN" value=""  onclick="clearCss(this);"   onblur="inputBlur(this);"><span id="oldPasswordNSpan" style="color: red;"></span></td>
                           		<td><input type="hidden" class="form-control form-control0" name="wuid" id="wid" value="${user.account}"></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">新密码</td>
                               <td><input type="password" class="form-control form-control0" id="newPassword" name="newPasswordN" value="" onblur="inputBlur(this);"  onclick="clearCss(this);" ><span id="newPasswordNSpan" style="color: red;"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">确认密码</td>
                               <td><input type="password" class="form-control form-control0" id="sureNewPassword" name="sureNewPasswordN" value="" onclick="clearCss(this);" onblur="inputBlur(this);" ><span id="sureNewPasswordNSpan" style="color: red;"></span></td>
                           </tr>
                           
                       </table>
                       </form>
                       <div class="weui-pt30 weui-pb30 weui-t_c"><input type="button" onclick="savepassword('passwordform');" value="保存" class="btn btn-s weui-f16 weui-pl30 weui-pr30"></div>
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