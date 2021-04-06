<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/6 0006
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交易金额</title>
    <jsp:include page="/common/backend_common.jsp"/>
</head>
<body class="no-skin" style="background: white">

<!-- 内容 -->
<div class="main-content">
    <div class="main-content-inner">
        <!-- 导航路径 -->
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="/sys/admin/index.page">Home</a>
                </li>
                <li class="">交易管理</li>
                <li class="">交易金额</li>
            </ul><!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <form class="form-search">
             <span class="input-icon">
                 <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                 <i class="ace-icon fa fa-search nav-search-icon"></i>
             </span>
                    <a href="/shoppingcart/cart.page">
                        <span class="menu-icon fa fa-shopping-cart" style="margin-left: 30px; color: coral; font-size: 30px"></span>
                    </a>
                </form>
            </div>
        </div>
        <div class="page-content">
            <div class="page-header">
                <h1>交易金额
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        交易总金额与订单总数
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12 ">
                    <div class="col-xs-5 label label-danger" style="height: 250px;line-height: 250px;font-size: 40px">
                        交易总金额 ：￥ <b id="money">0.00</b> 元
                    </div>
                    <div class="col-xs-5 col-xs-offset-1 label label-success" style="height: 250px;line-height: 250px;font-size: 40px">
                        交易总订单数 ：<b id="num">0</b>
                    </div>
                </div><!-- /.col-xs-12 -->
            </div><!-- /.row -->
        </div> <!--/.page-content-->
    </div><!--/.main-content-inner-->
</div><!--/.main-content-->

<script>

    $(function () {

        $.ajax({
            url: "/trade/countMoney.json",
            type : "POST",
            success : function (result) {
                if (result.status == "success"){
                    $("#money").html(result.data);
                }
            }
        })

        $.ajax({
            url: "/trade/countOrder.json",
            type : "POST",
            success : function (result) {
                if (result.status == "success"){
                    $("#num").html(result.data);
                }
            }
        })

    });

</script>

</body>
</html>
