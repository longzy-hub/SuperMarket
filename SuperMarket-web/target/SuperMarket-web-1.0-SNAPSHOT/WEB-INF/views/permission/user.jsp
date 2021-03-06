<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/11 0011
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
    <jsp:include page="/template/permission/roleListTemplate2.jsp"/>
    <jsp:include page="/template/permission/userListTemplate.jsp"/>
    <jsp:include page="userForm/userForm.jsp"/>
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
                <li class="">权限管理</li>
                <li class="">用户管理</li>
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
                <h1>用户管理
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        用户列表
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-2">
                        <div class="table-header">
                            角色列表&nbsp;&nbsp; <a class="green" href="#"></a>
                        </div>
                        <!-- 读取角色 -->
                        <div id="roleList"></div>

                    </div>
                    <div class="col-xs-10">
                       <!-- <ul class="list-inline fa-border">
                            <li><button type="button" class="btn btn-warning fa fa-plus user-add">添加用户</button></li>
                        </ul>-->
                        <div class="col-xs-12 fa-border" >
                            <ul class="list-inline">
                                <li>展示&nbsp;&nbsp;<select id="pageSize">
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select>&nbsp;&nbsp;条记录</li>&nbsp;&nbsp;&nbsp;&nbsp;
                                <li><input type="search" id="keyword" name="keyword" placeholder="关键字"/></li>&nbsp;&nbsp;&nbsp;&nbsp;
                                <li>状态&nbsp;&nbsp;<select id="search_status">
                                    <option value="1">有效</option>
                                    <option value="0">无效</option>
                                </select></li>&nbsp;&nbsp;&nbsp;&nbsp;
                                <li><button class="btn btn-info fa fa-check research">查询</button></li>
                            </ul><!--/.ul-->
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>用户名</th>
                                        <th>所属角色</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>创建时间</th>
                                        <th>更新时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody id="userList"></tbody>
                            </table>
                            <div class="row" id="userPage"></div>
                        </div>
                    </div>
                </div>
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content-inner -->
</div><!-- /.main-content -->

<!-- 引入js文件-->
<script src="/js/permission/user.js"></script>
</body>
</html>
