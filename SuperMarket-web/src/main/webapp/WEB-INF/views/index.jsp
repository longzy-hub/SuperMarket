<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/11 0011
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统首页</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <script src="/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="no-skin" style="background: white">
    <!-- 导航路径 -->
    <div class="main-content-inner">
        <!-- 导航路径 -->
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="/sys/admin/index.page">Home</a>
                </li>
                <li class="">系统首页</li>
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
                <h1>系统首页
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        超市描述
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12 ">
                    <div class="col-xs-10 col-xs-offset-1">
                        <%--轮播图--%>
                        <div id="myCarousel" class="carousel slide" style="width: 1000px; height: 350px;">
                            <!-- 轮播（Carousel）指标 -->
                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                            </ol>
                            <!-- 轮播（Carousel）项目 -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="/img/part1.jpg" alt="First slide">
                                </div>
                                <div class="item">
                                    <img src="/img/part3.jpg" alt="Second slide">
                                </div>

                            </div>
                            <!-- 轮播（Carousel）导航 -->
                            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                        <h3>超市描述：为在校师生提供在线购买所需商品并送货上门</h3>
                        <h3>超市地址：贵工程校园里的绣山生活馆里</h3>
                    </div>

                </div><!-- /.col-xs-12 -->
            </div><!-- /.row -->
        </div> <!--/.page-content-->
    </div>

<script>
    $("#slidershow").carousel({
        interval: 3000
    });
</script>
</body>
</html>
