<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- 让IE浏览器运行最新的渲染模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<!-- 可以让部分国产浏览器默认采用高速模式渲染页面 -->
	<meta name="renderer" content="webkit">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="0">
	<title>首页</title>
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

  function submitForm(){
	 var account  =  $("#account").val();
	 var password = $("#password").val();
	 if(account == null || account == ""){
		 $("#accountSpan").html("请正确填写账户！"); 
		 return false;
	 }
	 
	 if(password == null || password == ""){
		 $("#passwordSpan").html("请正确输入密码！"); 
		 return false;
	 }
	 
	    //NO_USER :用户不存在
		//BUG_USER ： 用户信息不完整
		//FULL_USER ：用户信息完整
	 $.ajax({
			   type: "POST",
			   url: "login",
			   async:false,
			   data: {
				  'account':account, 
				  'password':password
			   },
			   success: function(msg){
				     if(msg =="NO_USER"){
				    	 alert("用户不存在，请注册后在登录");
				    	 window.location.href="registerOne";	
				     }
					 if(msg == "BUG_USER"){
						 alert("登录成功，请完善个人信息！"); 
						 window.location.href="registerTwo";	
					 }  
					 if(msg == "FULL_USER"){
						 window.location.href="myCourse";	
					 }  
			   }
		});
	 
	  
  }

  $(function(){
	  $.ajax({
		   type: "POST",
		   url: "getNotice",
		   data: {},
		   async:false,
		   success: function(jsonss){
			   var jsons = JSON.parse(jsonss);
			   for(var p in jsons){
			      var inde="#"+p;
		     	  $(inde).append(jsons[p]);
				 }
		   }
		});
});

</script>
<body>
<div class="top">
     <div class="container">
          <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 weui-c_9"><i class="glyphicon glyphicon-home"></i> 南开主页</div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6  weui-t_r"><a href="registerOne" class="weui-c_9">注册</a></div>
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
    <div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators carousel-indicators1">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>   
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner" id="picture">
      
    </div>
    </div>
</div>

<div class="container"  >
     <div class="row" >
     <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="float: left;display:inline;">
          <h3 class="clearfix" >
              <span class="weui-fl weui-f20"><img src="/static/images/home_10.png"> <span class="weui-v_m weui-pl10">通知公告</span></span>
              <a href="noticeOne" class="weui-fr weui-c_9 weui-pt10">更多 >></a>
          </h3>
          <ul class="newslist weui-pt5" id="notice">
          </ul>
     </div>
     <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="float: left;display:inline;">
          <h3 class="clearfix">
              <span class="weui-fl weui-f20"><img src="/static/images/home_16.png"> <span class="weui-v_m weui-pl10">资讯动态</span></span>
              <a href="dynamicsInformation" class="weui-fr weui-c_9 weui-pt10">更多 >></a>
          </h3>
          <ul class="newslist weui-pt5" id="dynamicsInformation">
              
          </ul>
     </div>
     <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 " style="float: left;display:inline;">
          <h3 class="clearfix">
              <span class="weui-fl weui-f20"><img src="/static/images/home_13.png"> <span class="weui-v_m weui-pl10">培训项目</span></span>
              <a href="trainingProjects" class="weui-fr weui-c_9 weui-pt10">更多 >></a>
          </h3>
          <ul class="newslist weui-pt5" id="trainingProjects">
          </ul>
     </div>
   </div>
</div>


<div class="container weui-pb10">
     <div class="row"   >
          <div  class="col-lg-8 col-md-8 col-sm-8 col-xs-8 " >
               <h3 class="clearfix" >
                 <span class="weui-fl weui-f20"><img src="/static/images/home_25.png"> <span class="weui-v_m weui-pl10">在线课程</span></span>
                 <a href="onlineCourseOne" class="weui-fr weui-c_9 weui-pt10">更多 >></a>
               </h3>
               <ul class="clearfix proul weui-f16 weui-lh30" id="onlineCourse">
                  
               </ul>
          </div>
          <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
              <div class="lobox">
                    <h4><img src="/static/images/home_36.png"> <span class="weui-v_m">用户登录</span></h4>
                     <a name="tips" href="#tips"> </a> 
                    <div class="weui-pl30 weui-pr30 weui-pt20 weui-ml20 weui-mr20">
                     <form action="login"  method="post">
                         <div class=" weui-p_r">
                             <span class="lo_ico"><i class="glyphicon glyphicon-user"></i></span>
                             <input type="text" class="form-control form-control1" id="account" name="account" value="" placeholder="请输入登录账户" onfocus="javascript:clearSpan('accountSpan');"><span style="color: red;" id="accountSpan"></span>
                         </div>
                         <div class=" weui-p_r weui-mt15">
                             <span class="lo_ico"><i class="glyphicon glyphicon-lock"></i></span>
                             <input type="password" class="form-control form-control1"  name="password"  id="password"  value=""  placeholder="请输入登录用户密码" onfocus="javascript:clearSpan('passwordSpan');"><span style="color: red;" id="passwordSpan" ></span>
                         </div>
                             
                         <p class="weui-f16 weui-pt10"><a href="passwordRetrieval" class="weui-c_9">找回密码</a></p>
                         <div class="weui-t_c weui-pt5">
                             <a href="javascript:void(0);"><input type="button" value="登录" class="btn btn-s weui-f16 weui-pl25 weui-pr25" onclick="javascript:submitForm();"></a>
                             <a href="registerOne"  class="btn btn-s weui-f16 weui-pl25 weui-pr25 weui-ml30">注册</a>
                         </div>
                         </form>
                         
                    </div>
              </div>
          </div>
     </div>
</div>

<div class="footer weui-t_c weui-lh30" style="position:static!important;" >
     相关链接：	<a href="#" class="weui-pl20 weui-pr20">南开大学</a>	|	<a href="#" class="weui-pl20 weui-pr20">人力资源社会保障部</a>	|	<a href="#" class="weui-pl20 weui-pr20">教师发展中心</a>	|	<a href="#" class="weui-pl20 weui-pr20">天津市人社局</a>	|	<a href="#" class="weui-pl20 weui-pr20">继续教育学院</a><br>
     地址：天津市南开区卫津路94号	<span class="weui-pl15">电话：022-23501215</span>		<span class="weui-pl15">邮编：041588</span>	<span class="weui-pl15">皖ICP备12003308号-1 Power by DedeCms</span><br>
     版权所有：南开大学继续教育学部
</div>

</body>
</html>