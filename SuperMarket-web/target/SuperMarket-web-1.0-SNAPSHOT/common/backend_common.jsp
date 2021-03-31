<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta charset="utf-8"/>
<title>贵工程超市平台</title>
<meta name="description" content="overview &amp; stats"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/bootstrap-3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<%--<link rel="stylesheet" href="/assets/css/fonts.googleapis.com.css" />--%>

<!-- ace styles -->
<link rel="stylesheet" href="/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
<link rel="stylesheet" href="/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
<![endif]-->
<link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="/assets/css/jquery.gritter.min.css"/>
<link rel="stylesheet" href="/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="/assets/css/chosen.min.css"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="/assets/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="/assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->


<script src="/js/html5shiv.min.js"></script>
<script src="/js/respond.min.js"></script>

<script src="/js/jquery-1.10.2.min.js"></script>
<script src="/assets/js/jquery-ui.min.js"></script>
<script src="/assets/js/jquery.gritter.min.js"></script>
<script src="/assets/js/chosen.jquery.min.js"></script>

<%--<script src="/bootstrap-3.3.7/js/bootstrap.min.js"></script>--%>
<script src="/js/date.js"></script>
<script src="/js/mustache.js"></script>

<script type="text/javascript">
    // 展示提示信息
    function showMessage(title, msg, isSuccess) {
        if (!isSuccess) {
            msg = msg || '操作失败';
        } else {
            msg = msg || '操作成功'
        }
        $.gritter.add({
            title: title,
            text: msg ,
            time: 1500,
            class_name: isSuccess ? 'gritter-success gritter-center gritter-light' : 'gritter-warning gritter-center gritter-light'
        });
    }
</script>

