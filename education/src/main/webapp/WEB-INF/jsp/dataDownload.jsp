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
	<title>资料下载</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">

</head>
<body>
<div class="top">
     <div class="container">
          <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 "><i class="glyphicon glyphicon-home"></i> 南开主页</div>
                <div class="col-lg-6 col-md-6 col-sm-6 weui-t_r">${user.realName}<a href="logout">退出</a></div>
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
               <li class="weui-fl"><a href="myCourse">我的课程</a></li>
               <li class="weui-fl"><a href="dataDownload" class="on">资料下载</a></li>
               <li class="weui-fl"><a href="personalData">个人中心</a></li>
           </ul>
     </div>
</div>

<div class="container ">
     <div class="row ">
          <div class="col-lg-3 col-md-3 col-sm-3  weui-pt20">
               <div class="contentus weui-f16">
                   <div class=" weui-pl15 weui-pr15">
                    <h4 class="weui-f20  weui-pb10">联系我们</h4>
                    <p>电话：022-60784569</p>
                    <p>邮箱：jxjy@nankai.edu.cn</p>
                    <p>地址：南开大学主楼448</p>
                    </div>
               </div>
          </div>
          <div class="col-lg-9 col-md-9 col-sm-9 weui-f16 weui-pt10">
               <ul class="weui-f16 newsul">
                   <li class="clearfix">
                        <a href="#" class="weui-fl">关于在学生党员中开展“不忘初心 牢记使命 放飞青春梦想”学习宣传贯彻党的十九大精神系列活动的通知</a>
                        <span class="weui-fr">2017-12-20</span>
                   </li>
               </ul>
               
               
               
               <div class="page weui-pt30 weui-pb30 weui-t_c weui-f20">
                    <div class="form-inline" id="pageLine">
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
<script src="js/tab.js"></script>

</body>
</html>