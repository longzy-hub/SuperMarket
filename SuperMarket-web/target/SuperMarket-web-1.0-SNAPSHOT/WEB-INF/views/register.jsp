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

    <title>注册账号</title>

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
    <h3>用户注册</h3>
    <form class="form-login" id="register" method="post">
        <ul>
            <li class="form-group has-success has-feedback">
                <span class="glyphicon glyphicon-earphone form-control-feedback"></span>
                <input type="text" class="form-control" placeholder="请输入手机号" name="telephone" id="telephone" required />
            </li>
            <li class="form-group has-success has-feedback">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <input type="text" class="form-control" placeholder="请输入用户名" name="username" id="username" required />
            </li>
            <li class="form-group has-success has-feedback">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                <input type="password" class="form-control" placeholder="请输入密码" id="password"name="password" required />
            </li>
            <li class="form-group has-success has-feedback">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                <input type="text" class="form-control" placeholder="请输入您的qq邮箱" name="mail" id="mail" required />
            </li>
            <li class="form-group has-success has-feedback">
                <span class="glyphicon glyphicon-check form-control-feedback"></span>
                <input type="text" class="form-control code" placeholder="请输入6位验证码" id="mailCode" name="mailCode" />
                <a id="send" href="javascript:void(0)" class="btn btn-primary btn-sm"
                   style="width: 100px;float: right; margin-top: -30px; margin-right: 10px">
                    发送邮件
                </a>
            </li>
            <li>
                <span>已有账号？请点击&nbsp;<a href="/sys/login/login.page">登录账号</a></span>
            </li>
            <li>
                <button id="submit" type="submit" class="btn btn-primary btn-lg btn-block">注册</button>
            </li>
        </ul>
    </form>
</div>

<script>
    $(function () {
        var mail;   // 邮箱
        var mailCode ;// 验证码

        $('#send').click(function () {
            $(this).html("已发送");
            $(this).attr("class","btn btn-default btn-sm");
            mail = $('#mail').val();
            $.ajax({
                url: "/sys/mail/sendMail.json",
                type: "POST",
                data: {
                   mail : mail
                },
                success : function (result) {
                     bindSubmitClick(result);
                }
            });
        });
        
        function bindSubmitClick(result) {

            $("#submit").click(function () {
                // telephone = $('#telephone').val();
                // username = $('#username').val();
                // password = $('#password').val();
                // mail = $('#mail').val();
                mailCode = $('#mailCode').val();
                if (result == mailCode){
                    alert("注册成功，请登录！")
                    $('#register').attr("action", "/sys/login/register.json");
                    // $.ajax({
                    //     url : "/sys/login/register.json",
                    //     type: "POST",
                    //     data:{
                    //         telephone : telephone,
                    //         username : username,
                    //         password : password,
                    //         mail : mail
                    //     }
                    // });
                }else {
                    alert("验证码错误，请重新注册");
                }
            });
        }
    });
</script>

</body>
</html>








