<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/1 0001
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
    <jsp:include page="/template/personal/personalTemplate.jsp"/>
    <jsp:include page="/template/personal/personalListTemplate.jsp"/>
    <jsp:include page="personalForm/personalForm.jsp"/>
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
                <li class="">个人信息</li>
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
                <h1>个人信息
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        个人信息设置
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">个人信息</h4>
                            </div>
                            <div id="personal" class="panel-body"   >

                            </div>
                        </div>
                    </div>

                    <div class="col-xs-9">
                        <div class="col-xs-12 fa-border">
                            <ul class="list-inline">
                                <li>展示&nbsp;&nbsp;<select id="pageSize">
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                </select>&nbsp;&nbsp;条记录</li>&nbsp;&nbsp;&nbsp;&nbsp;
                            </ul><!--/.ul-->
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>登录状态</th>
                                    <th>登录时间</th>
                                </tr>
                                </thead>
                                <tbody id="personalList"></tbody>
                            </table>
                            <div class="row" id="personalPage"></div>
                        </div>
                    </div>
                </div>
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content-inner -->
</div><!-- /.main-content -->

<script src="/js/personal/personal.js"></script>

</body>
</html>
