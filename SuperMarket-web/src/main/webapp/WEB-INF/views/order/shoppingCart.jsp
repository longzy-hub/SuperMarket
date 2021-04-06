<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/3 0003
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
    <jsp:include page="/template/order/cartListTemplate.jsp"/>
    <jsp:include page="cartForm/cartForm.jsp"/>
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
                <li class="">我的订单</li>
                <li class="">购物车</li>
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
                <h1>购物车
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        购物车详情
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12 ">
                    <ul class="list-inline fa-border">
                        <li><button id="pay" type="button" class="btn btn-warning fa fa-cc-paypal disabled" >去付款</button></li>
                        <li>应付&nbsp;&nbsp;<span class="red" id="pay-money"></span>&nbsp;&nbsp;元</li>
                        <li class="red pull-right">购买商品，价格超过30元起送</li>
                    </ul>
                    <div class="col-xs-12 fa-border" >
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>商品图片</th>
                                <th>商品名称</th>
                                <th>商品单价(元)</th>
                                <th>购买数量</th>
                            </tr>
                            </thead>
                            <tbody id="cartList">
                            </tbody>
                        </table>
<%--                        <div class="row" id="backPage"></div>--%>
                    </div>
                </div><!-- /.col-xs-12 -->
            </div><!-- /.row -->
        </div> <!--/.page-content-->
    </div><!--/.main-content-inner-->
</div><!--/.main-content-->

<script src="/js/order/shoppingCart.js"></script>

</body>
</html>
