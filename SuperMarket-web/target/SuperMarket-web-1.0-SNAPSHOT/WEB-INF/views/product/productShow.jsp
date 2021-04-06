<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/29 0029
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品展示</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
    <jsp:include page="/template/product/categoryListTemplate2.jsp"/>
    <jsp:include page="/template/product/productShowTemplate.jsp"/>
    <jsp:include page="showProduct/showProductDetails.jsp"/>
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
                <li class="">商品展示</li>
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
                <h1>商品展示
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        商品分类与商品列表
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-2">
                        <div class="table-header">
                            商品分类列表&nbsp;&nbsp; <a class="green" href="#"></a>
                        </div>
                        <!-- 读取分类列表 -->
                        <div id="categoryList"></div>
                    </div>
                    <div class="col-xs-10">
                        <div class="col-xs-12 fa-border">
                            <ul class="list-inline">
                                <li>展示&nbsp;&nbsp;<select id="pageSize">
                                    <option value="12">12</option>
                                    <option value="36">36</option>
                                    <option value="72">72</option>
                                    <option value="100">100</option>
                                </select>&nbsp;&nbsp;条记录</li>&nbsp;&nbsp;&nbsp;&nbsp;
                                <li><input type="search" id="keyword" name="keyword" placeholder="关键字"/></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <li class="hidden"><input id="search_status" value="1"/></li>
                                <li><button class="btn btn-info fa fa-check research">查询</button></li>
                            </ul><!--/.ul-->
                            <!--商品展示-->
                            <div  id="showProductList">

                            </div>
                            <div class="row" id="showProductPage"></div>
                        </div>
                    </div>
                </div>
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content-inner -->
</div><!-- /.main-content -->

<script src="/js/product/productShow.js"></script>
</body>
</html>
