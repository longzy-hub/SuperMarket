<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/common/backend_common.jsp"/>
</head>
<body class="no-skin">

<div id="navbar" class="navbar navbar-default ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header pull-left">
            <a href="/sys/admin/admin.page" class="navbar-brand">
                <small>
                    <i class="fa fa-university"></i>
                    贵工程超市平台
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <span class="user-info"><small>欢迎,</small><shiro:principal/></span>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>
                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-user"></i>
                                个人信息
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/sys/login/logout.page">
                                <i class="ace-icon fa fa-power-off"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>
    <div id="sidebar" class="sidebar responsive ace-save-state">
        <script type="text/javascript">
            try{ace.settings.loadState('sidebar')}catch(e){}
        </script>
        <!-- 侧导航栏 -->
        <ul class="nav nav-list">
            <li class="active">
                <a class="popstyle"  target="_blank" href="/sys/admin/index.page">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> 系统首页 </span>
                </a>
                <b class="arrow"></b>
            </li>
            <shiro:hasAnyRoles name="超级管理员,权限管理员,商品管理员">
                <shiro:hasPermission name="perm:*:*">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-users"></i>
                            <span class="menu-text">权限管理	</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a class="popstyle" target="_blank" href="/sys/permission/user.page">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    用户管理
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="/sys/permission/role.page">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    角色管理
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="/sys/permission/perm.page">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    权限管理
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="product:*:*">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-desktop"></i>
                            <span class="menu-text">商品管理</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a class="popstyle" target="_blank" href="/category/category.page">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    商品分类
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="/product/product.page">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    商品列表
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>
            </shiro:hasAnyRoles>
            <li class="">
                <a  class="popstyle" target="_blank" href="/product/productShow.page">
                    <i class="menu-icon fa fa-table"></i>
                    <span class="menu-text">商品展示</span>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-list-alt"></i>
                    <span class="menu-text">我的订单	</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li class="">
                        <a class="popstyle" target="_blank" href="#">
                            <i class="menu-icon fa fa-caret-right"></i>
                            购物车
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-caret-right"></i>
                            <span class="menu-text">全部订单</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a class="popstyle" target="_blank" href="#">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    已完成
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="#">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    待付款
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="#">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    待发货
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="#">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    待收货
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <shiro:hasAnyRoles name="超级管理员,订单管理员,配送管理员">
                <shiro:hasPermission name="order:*:*">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-list"></i>
                            <span class="menu-text"> 交易管理 </span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a class="popstyle" target="_blank" href="#">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    交易金额
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a class="popstyle" target="_blank" href="#">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    订单处理
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="back:*:*">
                    <li class="">
                        <a  class="popstyle" target="_blank" href="">
                            <i class="menu-icon fa fa-truck"></i>
                            <span class="menu-text"> 配送管理 </span>
                        </a>
                        <b class="arrow"></b>
                    </li>
                </shiro:hasPermission>
            </shiro:hasAnyRoles>
            <li class="">
                <a class="popstyle" target="_blank" href="">
                    <i class="menu-icon fa fa-user"></i>
                    <span class="menu-text"> 个人信息 </span>
                </a>
                <b class="arrow"></b>
            </li>
        </ul><!-- /.nav-list -->

        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>
    </div>

    <div class="main-content">
        <!-- 设置每个页面显示位置 -->
        <div class="main-content">
            <iframe id="innerFrame" src="/sys/permission/perm.page" width="100%" style="min-height: 768px;"></iframe>
        </div>
    </div><!-- /.main-content -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="/assets/js/jquery-ui.custom.min.js"></script>
<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="/assets/js/jquery.easypiechart.min.js"></script>
<script src="/assets/js/jquery.sparkline.index.min.js"></script>
<script src="/assets/js/jquery.flot.min.js"></script>
<script src="/assets/js/jquery.flot.pie.min.js"></script>
<script src="/assets/js/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<script>
    $(document).ready(function() {
        // 给class为popstyle的删除属性target
        $(".popstyle").removeAttr("target");
        $(".popstyle").each(function() {
            var $this = $(this)
            tmp = $this.attr("href");
            $this.attr("data", tmp);
            $this.attr("href", "javascript:void(0)");
        })
        // 点击class为popstyle的导航 页面的显示位置
        $(".popstyle").click(function() {
            var $this = $(this)
            $("iframe").attr('src', $this.attr("data"));
        });
        // 点击导航后，active的位置
        /*$("ul.nav-list > li").click(function (e) {
            // 阻止当前默认动作
            e.preventDefault();
            $("ul.nav-list > li").removeClass("active");
            $(this).addClass("active");
        });*/
        // 点击导航后，active的位置
        $(".popstyle").click(function (e) {
            // 阻止当前默认动作
            e.preventDefault();
            $(".popstyle").parent().removeClass("active");
            $(this).parent().addClass("active");
        });

    });
</script>

</body>
</html>
