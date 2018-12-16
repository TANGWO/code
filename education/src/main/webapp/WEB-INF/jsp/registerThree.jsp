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
               <li class="weui-fl"><a href="index">首页</a></li>
               <li class="weui-fl"><a href="2基地介绍.html">基地介绍</a></li>
               <li class="weui-fl"><a href="3-1通知公告.html">通知公告</a></li>
               <li class="weui-fl"><a href="4资讯动态.html">资讯动态</a></li>
               <li class="weui-fl"><a href="5培训项目.html">培训项目</a></li>
               <li class="weui-fl"><a href="6-1在线课程.html">在线课程</a></li>
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
                  <li>
                      <a href="javascripot:;">
                         <i class="i_g"></i>
                         <span>个人信息</span>
                      </a>
                  </li>
                  <li class="active">
                      <a href="javascripot:;" >
                         <i class="i_c"></i>
                         <span>注册成功</span>
                      </a>
                  </li>
          </ul>
          <div id="myTabContent" class="tab-content">
              <div class="tab-pane fade in active" id="home">
                    <div class="weui-f20 weui-red01 weui-t_c weui-pt30">
                        <img src="/static/images/6_03.png">
                        <p class="weui-pt20">恭喜！注册成功！</p>
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

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tab.js"></script>

</body>
</html>