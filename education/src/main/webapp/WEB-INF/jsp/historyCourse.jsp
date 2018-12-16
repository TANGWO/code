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
	<title>历史课程</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<style type="text/css">
	input::-webkit-outer-spin-button,input::-webkit-inner-spin-button{
	   -webkit-appearance: none !important;
	}
	input[type="number"]{-moz-appearance:textfield;}
	#pageLine .weui-pr15 {
  		display: inline-block;
 		verticla-align: middle;
	}

</style>
</head>
<script type="text/javascript">
$(function(){
	 $.ajax({
		   type: "POST",
		   url: "myCourse",
		   data: {
			   'page':'1',
			   'size':'10',
			   'status':'2'
		   },
		   success: function(msg){
			   var info = JSON.parse(msg);
			   console.log(info);
			   if(info){
			     var course=info['info'];
			     var link=info['link'];
			     var pageLine=info['pageLine']; 
			     $("#mycourse").empty();
		     	 $("#mycourse").append(course);
		     	 if(course){
			     	 $("#pageLine").empty();
			     	 $("#pageLine").append(pageLine);
		     	 }else{
		     		 $("#mycourse").empty();
				     $("#mycourse").append("未查到任何数据");
		     	 }
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
		   url: "myCourse",
		   data: {
			   'page':v,
			   'size':'10',
			   'status':'2'
		   },
		   success: function(msg){
			   var info = JSON.parse(msg);
			   //console.log(info['context']);
			   if(info){
				     var course=info['info'];
				     var link=info['link'];
				     var pageLine=info['pageLine']; 
				     $("#mycourse").empty();
			     	 $("#mycourse").append(course);
			     	 if(course){
				     	 $("#pageLine").empty();
				     	 $("#pageLine").append(pageLine);
			     	 }else{
			     		 $("#mycourse").empty();
					     $("#mycourse").append("未查到任何数据");
			     	 }
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
</script>

<body>
<div class="top">
     <div class="container">
          <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 weui-c_9"><i class="glyphicon glyphicon-home"></i> 南开主页</div>
                <div class="col-lg-6 col-md-6 col-sm-6 weui-t_r">${user.realName}&emsp;&emsp;&emsp;<a href="logout">退出</a></div>
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
               <li class="weui-fl"><a href="myCourse" class="on">我的课程</a></li>
               <li class="weui-fl"><a href="dataDownload">资料下载</a></li>
               <li class="weui-fl"><a href="personalData">个人中心</a></li>
           </ul>
     </div>
</div>

<div class="container">
     <div class="row">
          <div class="col-lg-3 col-md-3 col-sm-3  col-xs-3  weui-pt20">
               <ul class="menu weui-t_c">
                   <li><a href="myCourse">我的课程</a></li>
                   <li><a href="historyCourse" class="on">历史课程</a></li>
               </ul>
               <div class="contentus weui-mt10 weui-f16">
                   <div class=" weui-pl15 weui-pr15" id='link'>
                    </div>
               </div>
          </div>
          <div class="col-lg-9 col-md-9 col-sm-9 weui-f16" id="mycourse">
               
               
          </div>
          <div class="page weui-pt30 weui-pb30 weui-t_c weui-f20">
               <div class="form-inline" id="pageLine">
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