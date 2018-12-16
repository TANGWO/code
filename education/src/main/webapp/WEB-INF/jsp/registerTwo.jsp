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
	<title>注册</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">

</head>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<script type="text/javascript">
	/* 清楚span信息 */
	function clearSpan(s){
		var spanId="#"+s;
		$(spanId).html("");
	}
	
	/*验证手机号格式  */
	function checkPhone(){
		var flag = false;
		var reg =/^1[3|4|5|8][0-9]\d{4,8}$/;
		var phone = $("#phone").val();
		if(!reg.test(phone)){
			flag = false;
			 $("#phoneSpan").html("请输入正确的手机号码！"); 
			 return false;
		}else{
			flag = true;
		}
		return flag;
	}
	
	/* 验证身份证件号格式 */
	function checkIdCard(){
		var flag = false;
		var reg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i; 
		var idCard = $("#idCard").val();
		if(!reg.test(idCard)){
			flag = false;
			$("#idCardSpan").html("请输入正确的身份证件号码！"); 
			return false;
		}else{
			flag = true;
		}
		return flag;
	}
	
	function submitForm(){
		var phone = $("#phone").val();
		var idCard = $("#idCard").val();
		var realName = $("#realName").val();
		var workUnit = $("#workUnit").val();
		var politicalStatus = $("#politicalStatus").val();
		var technicalPosition = $("#technicalPosition").val();
		var educationalBackground = $("#educationalBackground").val();
		
		if(realName == null || realName == ""){
			$("#realNameSpan").html("真实姓名不能为空"); 
			return false;
		}
		if(phone == null || phone == ""){
			$("#idCardSpan").html("电话号码不能为空！"); 
			return false;
		}
		if(idCard == null || idCard == ""){
			$("#idCardSpan").html("身份证件号码不能为空！"); 
			return false;
		}
		
		if(workUnit == null || workUnit == ""){
			$("#workUnitSpan").html("工作单位不能为空！"); 
			return false;
		}
		if(politicalStatus == null || politicalStatus == ""){
			$("#politicalStatusSpan").html("政治面貌不能为空！"); 
			return false;
		}
		if(technicalPosition == null || technicalPosition == ""){
			$("#technicalPositionSpan").html("技术职务不能为空！"); 
			return false;
		}
		if(educationalBackground == null || educationalBackground == ""){
			$("#educationalBackgroundSpan").html("学历信息不能为空！"); 
			return false;
		}
		
		
		$.ajax({
			   type: "POST",
			   url: "consummateInfo",
			   data: {
				   'phone':phone,  
				   'idCard':idCard,
				   'realName':realName,
				   'workUnit':workUnit,
				   'politicalStatus':politicalStatus,
				   'technicalPosition':technicalPosition,
				   'educationalBackground':educationalBackground
			   },
			   async:false,
			   success: function(msg){
				    //ERR_INFO      : 更新信息错误
					//NO_USER		：用户session过期
					//SUCCESS_INFO  ：更新信息成功
				   
			     if(msg=="SUCCESS_INFO"){
			    	 window.location.href="registerThree";	
			     }else if(msg=="ERR_INFO"){
			    	 alert("信息提交失败，请联系管理员！");
			    	 return false;
			     }else{
			    	 alert("由于你长时间离开，请登录后在提交信息！");
			    	 window.location.href="index"; 
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
                  <li>
                      <a href="javascripot:;" >
                        <i class="i_z"></i>
                        <span>账号信息</span>
                      </a>
                  </li>
                  <li class="active">
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
                     <div class="col-lg-8 col-md-8 col-sm-8 col-lg-push-2 col-md-push-2 col-sm-push-2">
                       <table width="100%" class="table1 weui-f20">
                           <tbody><tr>
                               <td class="weui-t_r" width="30%">真实姓名</td> 
                               <td><input type="text" class="form-control form-control0" name="realName" id="realName"  value=""  onfocus="javascript:clearSpan('realNameSpan');" > <span style="color: red;" id="realNameSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">手机号</td>
                               <td><input type="phone" class="form-control form-control0" name="phone" id="phone"  value=""  onfocus="javascript:clearSpan('phoneSpan');" onblur="javascript:checkPhone();"><span style="color: red;" id="phoneSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">身份证号</td>
                               <td><input type="text" class="form-control form-control0" name="idCard" value="" id="idCard"  onfocus="javascript:clearSpan('idCardSpan');" onblur="javascript:checkIdCard();"><span style="color: red;" id="idCardSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">工作单位</td>
                               <td><input type="text" class="form-control form-control0" name="workUnit" value="" id="workUnit" onfocus="javascript:clearSpan('workUnitSpan');" ><span style="color: red;" id="workUnitSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">政治面貌</td>
                               <td><input type="text" class="form-control form-control0" name="politicalStatus" value="" id="politicalStatus" onfocus="javascript:clearSpan('politicalStatusSpan');" ><span style="color: red;" id="politicalStatusSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">专业技术职务</td>
                               <td><input type="text" class="form-control form-control0" name="technicalPosition" value="" id="technicalPosition" onfocus="javascript:clearSpan('technicalPositionSpan');"><span style="color: red;" id="technicalPositionSpan"></span></td>
                           </tr>
                           <tr>
                               <td class="weui-t_r">学历信息</td>
                               <td><input type="text" class="form-control form-control0" name="educationalBackground" value="" id="educationalBackground" onfocus="javascript:clearSpan('educationalBackgroundSpan');" ><span style="color: red;" id="educationalBackgroundSpan"></span></td>
                           </tr>
                       </tbody></table>
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