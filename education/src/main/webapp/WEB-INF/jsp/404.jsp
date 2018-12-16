<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<title>哦喔，页面找不到了</title>
		<meta name="viewpoint" content="width=device-width,initial-scale=1">
		<link rel="stylesheet" href="/static/css/404.css" />
		<script src="/static/js/jquery-3.3.1.min.js"></script>
	</head>
	<body>
		<div class="code">
			<p>ERROR 404</p>
		</div>
		<div class="road">
			<div class="shadow">
				<div class="shelt">
					<div class="head">
						<div class="eyes">
							<div class="lefteye">
								<div class="eyeball"></div>
								<div class="eyebrow"></div>
							</div>
							<div class="righteye">
								<div class="eyeball"></div>
								<div class="eyebrow"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="hat"></div>
				<div class="bubble">
					<a href="index">Go back Home?</a>
				</div>
			</div>
			<p>PAGE NOT FOUND</p>
		</div>
	</body>
	<script type="text/javascript" src="/static/js/404.js" ></script>
</html>