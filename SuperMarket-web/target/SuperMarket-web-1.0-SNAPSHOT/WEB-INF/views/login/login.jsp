<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">

    <title>登陆</title>

	<!--引入bootstrap的css文件-->
	<link href="/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	
	<!--引入自定义css文件-->
    <link href="/css/login.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    
    <!--引入jquery-->
    <script src="/js/jquery-1.10.2.min.js"></script>
    
    <!--引入bootstrap的js文件-->
    <script src="/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="logintop">
		<span>欢迎登录贵工程超市平台</span>
	</div>

    <div class="loginbox row">
    	<h3>用户登录</h3>
    	<form class="form-login" action="" method="post">
    		<ul>
    			<li class="form-group has-success has-feedback">
    				<span class="glyphicon glyphicon-user form-control-feedback"></span>
					<input type="text" class="form-control" placeholder="账号" name="username" required />
    			</li>
    			<li class="form-group has-success has-feedback">
    				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
    				<input type="password" class="form-control" placeholder="密码" name="password" required="required" />
    			</li>
    			<li class="form-group has-success has-feedback">
    				<span class="glyphicon glyphicon-check form-control-feedback"></span>
    				<input type="text" class="form-control code" placeholder="验证码" name="vercode" required="required" />
					<canvas id="canvas"></canvas>
    			</li>
    			<li>
    				<span>还没有账号？请点击&nbsp;<a href="/sys/login/register.page">注册账号</a></span>
    			</li>
    			<li>
    				<button type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
    			</li>
    		</ul>
    	</form>
    </div>
    
	<!--引入login的js文件-->
	<script src="/js/login.js"></script>

</body>
</html>








