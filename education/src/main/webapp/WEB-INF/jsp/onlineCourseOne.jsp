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
	<title>在线课程</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">
<style>
	#pageLine .weui-pr15 {
  		display: inline-block;
 		verticla-align: middle;
	}

</style>
</head>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<script type="text/javascript">
 $(function(){
	 $.ajax({
		   type: "POST",
		   url: "getSimpleCourse",
		   data: {
			   'page':'1',
			   'size':'6',
			   
		   },
		   success: function(msg){
			   var info = JSON.parse(msg);
			   //console.log(info['context']);
			   if(info){
			     var context=info['context'];
			     var contexts = JSON.parse(context);
			     let infos = contexts['info'];
			     let link = contexts['link'];
			     var pageLine=info['pageLine']; 
			     $("#onlineCourse").empty();
		     	 $("#onlineCourse").append(infos);
		     	 $("#pageLine").empty();
		     	 $("#pageLine").append(pageLine);
		     	 $("#link").empty();
		     	 $("#link").append(link);
			   }else{
				   alert('未查到任何数据！'); return ;
			   }
		   }
		});
 });
 
 function getInfoByPage(v){
	 $.ajax({
		 type: "POST",
		   url: "getSimpleCourse",
		   data: {
			   'page':v,
			   'size':'6'
			   },
			   success: function(msg){
				   var info = JSON.parse(msg);
				   //console.log(info['context']);
				   if(info){
				     var context=info['context'];
				     var contexts = JSON.parse(context);
				     let infos = contexts['info'];
				     let link = contexts['link'];
				     var pageLine=info['pageLine']; 
				     $("#onlineCourse").empty();
			     	 $("#onlineCourse").append(infos);
			     	 $("#pageLine").empty();
			     	 $("#pageLine").append(pageLine);
			     	 $("#link").empty();
			     	 $("#link").append(link);
				   }else{
					   alert('未查到任何数据！'); return ;
				   }
			   }
		});
 }
 
   function getInfoByInputPage(){
	  var number = $("#inputPage").val();
	   let  n = /^[1-9]\d*$/; 
        if(!n.test(number)){
            alert('请输入正整数!')
            return ;
        }else{
			  getInfoByPage(number);
        }
   }
 
 
 </script >
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
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                    <a href="index" class="logo"><img src="/static/images/logo.png"> <span class="weui-v_m weui-pl10">国家级专业技术人员继续教育基地</span></a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 weui-pt15">
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
     <div class="row">
          <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
               <ul class="menu weui-t_c weui-mt10">
                   <li><a href="introduce">通知公告</a></li>
                   <li><a href="dynamicsInformation">资讯动态</a></li>
                   <li><a href="trainingProjects">培训项目</a></li>
                   <li><a href="onlineCourseOne" class="on">在线课程</a></li>
               </ul>
               <div class="contentus weui-mt10 weui-f16">
                   <div class=" weui-pl15 weui-pr15" id="link">
                   
                    </div>
               </div>
          </div>
          <div  class="col-lg-8 col-md-8 col-sm-8 col-xs-8" style="float: left; display:inline;" >
               <h4 class="clearfix weui-bb">
                   <span class="weui-fl titlespan">在线课程</span>
                   <span class="weui-fr weui-c_9 weui-pt15">当前位置：网站首页>在线课程</span>
               </h4>
               
               <div class="weui-pl30 weui-pr30 weui-pt10 weui-pb10" >
                    <div class="row" id="onlineCourse">
                    
                    </div>
               </div>
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


<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/tab.js"></script>

</body>
</html>