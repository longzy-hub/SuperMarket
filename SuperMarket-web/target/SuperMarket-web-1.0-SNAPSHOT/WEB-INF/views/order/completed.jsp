<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/3 0003
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已完成</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
    <jsp:include page="/template/order/completedListTemplate.jsp"/>
    <jsp:include page="orderForm/orderForm.jsp"/>
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
                <li class="">全部订单</li>
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
                <h1>全部订单
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        已完成
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12 ">
                    <div class="col-xs-12 fa-border" >
                        <ul class="list-inline">
                            <li>展示&nbsp;&nbsp;<select id="pageSize">
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>&nbsp;&nbsp;条记录</li>&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="hidden" id="search_status" value="4"/>
                        </ul><!--/.ul-->
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>订单号</th>
                                <th>联系方式</th>
                                <th>收货地址</th>
                                <th>付款价格(元)</th>
                                <th>收货时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="completedList">

                            </tbody>
                        </table>
                        <div class="row " id="completedPage"></div>
                    </div>
                </div><!-- /.col-xs-12 -->
            </div><!-- /.row -->
        </div> <!--/.page-content-->
    </div><!--/.main-content-inner-->
</div><!--/.main-content-->

<script src="/js/order/completed.js"></script>
</body>
</html>
