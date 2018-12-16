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
	<title>6-2在线课程</title>
<link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/static/css/style.css" type="text/css" rel="stylesheet">
<link href="/static/css/video-js.min.css" type="text/css" rel="stylesheet">
<style type="text/css">
.video-js .vjs-big-play-button{
    font-size: 2.5em;
    line-height: 2.3em;
    height: 2.5em;
    width: 2.5em;
    -webkit-border-radius: 2.5em;
    -moz-border-radius: 2.5em;
    border-radius: 2.5em;
    background-color: #73859f;
    background-color: rgba(115,133,159,.5);
    border-width: 0.15em;
    margin-top: -1.25em;
    margin-left: -1.75em;
}
/* 中间的播放箭头 */
.vjs-big-play-button .vjs-icon-placeholder {
    font-size: 1.63em;
}
/* 加载圆圈 */
.vjs-loading-spinner {
    font-size: 2.5em;
    width: 2em;
    height: 2em;
    border-radius: 1em;
    margin-top: -1em;
    margin-left: -1.5em;
}
.video-js.vjs-playing .vjs-tech {
    pointer-events: auto;
}
span{
	color:#0015ac;
}
span:hover{
	color:#aa4400;
}
span:active{
	color:#0055ff;
}
</style>
</head>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/tab.js"></script>
<script type="text/javascript" src="/static/js/video.min.js"></script>
<script type="text/javascript" src="/static/js/videojs-ie8.min.js"></script>
<script type="text/javascript" src="/static/js/zh-CN.js"></script>
<script type="text/javascript">
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

$(function(){
	$("#videoPannel").append("<video id='my-simple' class='video-js vjs-big-play-centered vjs-fluid' controls preload='none' data-setup='{}' width='800' height='490'  poster='none'>"+
			"<source src='getVideo?id=abcabc' type='video/mp4' id='videoResource' >"+
			"</video>");
	
	console.log($("#my-simple"));
	var id=$.getUrlParam('code');
	if(id==null || id==""){
		alert("获取视频资源失败！");
		return false;
	}
	 $.ajax({
		   type: "POST",
		   url: "getCatalogueById",
		   data: {
			   'id':id
		   },
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

<div class="container weui-lh30">
     <div class="row weui-pt15">
          <div class="col-lg-9 col-md-9 col-sm-9">
               <div class="tabbox" id="videoPannel">
                     
               </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-3" style="padding-left:0;">
              <div class="weui-bgcolor">
                  <div class="weui-f16 weui-pl15 weui-pt10 weui-pb10 weui-bb">课程列表</div>
              <div class="tabtitle" id="CourseCatalogue">
                   
              </div>
              </div>
          </div>
     </div>
     <div id="introduce">
	 </div>
</div>


<div class="footer weui-t_c weui-lh30" style="position:static!important;">
     相关链接：	<a href="#" class="weui-pl20 weui-pr20">南开大学</a>	|	<a href="#" class="weui-pl20 weui-pr20">人力资源社会保障部</a>	|	<a href="#" class="weui-pl20 weui-pr20">教师发展中心</a>	|	<a href="#" class="weui-pl20 weui-pr20">天津市人社局</a>	|	<a href="#" class="weui-pl20 weui-pr20">继续教育学院</a><br>
     地址：天津市南开区卫津路94号	<span class="weui-pl15">电话：022-23501215</span>		<span class="weui-pl15">邮编：041588</span>	<span class="weui-pl15">皖ICP备12003308号-1 Power by DedeCms</span><br>
     版权所有：南开大学继续教育学部
</div>
<script type="text/javascript">
function playVideo(v,th){
	
	
	  $(th).addClass("on").siblings().removeClass("on");
	  var videoPlayer = $("#my-simple")[0];
	  if(typeof(videoPlayer)!="undefined"){
		  var myPlayer = videojs('my-simple');
		  myPlayer.dispose();
	   } 
	  
	  $("#videoPannel").append("<video id='my-simple' class='video-js vjs-big-play-centered vjs-fluid' controls preload='none' data-setup='{}' width='800' height='490'  poster='none'>"+
				"<source src='getVideo?id="+'abccba'+"' type='video/mp4' id='videoResource' >"+
				"</video>");
	  //videoPlayer[0].webkitEnterFullscreen(); // webkit类型的浏览器
	 // videoPlayer[0].mozRequestFullScreen();  // FireFox浏览器
	  videojs('my-simple', {}, function(){//自动播放
		 var myPlayer = videojs('my-simple');
		     myPlayer.ready(function(){
			 var myPlayer = this;
			     myPlayer.play();
			 });
		 });
	}
</script>
</body>
</html>