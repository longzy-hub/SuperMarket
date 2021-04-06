$(function () {

    var productImgMap = {}; // 图片
    var categoryList; // 存储商品分类列表
    var categoryMap = {}; // 存储map格式的分类信息
    var productMap = {}; // 存储map格式的商品信息
    var pageNo; // 当前页码
    var pageSize;   // 每页显示条数
    var keyword;    // 关键字查询
    var search_status; // 状态
    var lastClickCategoryId = -1; // 最后一次选中分类id

    // 使用mustache模板
    var  categoryListTemplate2 = $('#categoryListTemplate2').html();
    Mustache.parse(categoryListTemplate2);

    var productShowTemplate = $('#productShowTemplate').html();
    Mustache.parse(productShowTemplate);

    loadCategoryList();

    // 查询操作
    $('.research').click(function (e) {
        // 阻止默认事件
        e.preventDefault();
        $("#showProductPage .pageNo").val(1);

        loadProductList(lastClickCategoryId);
    });

    // 渲染分类列表
    function loadCategoryList() {
        $.ajax({
            url : "/product/categoryList.json",
            type : "POST",
            success : function (result) {
                // 如果返回成功
                if (result.status == "success"){
                    // 渲染视图
                    var rendered = Mustache.render(categoryListTemplate2 , {
                        "categoryList" : result.data
                    });
                    categoryList = result.data;
                    // 将每一个分类添加到map中
                    $.each(result.data, function (i, category) {
                        categoryMap[category.id] = category;
                    });
                    // 在指定id下填充数据页面样式
                    $('#categoryList').html(rendered);
                    // 绑定操作
                    bindCategoryClick();
                }else {
                    showMessage("加载分类列表", result.msg, false);
                }
            }
        });
    }

    // 绑定操作
    function bindCategoryClick() {

        $('.category-name').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取分类id
            var categoryId = $(this).attr("data-id");
            handleCategorySelected(categoryId);
        });
    }

    // 点击分类后的变化
    function  handleCategorySelected(categoryId) {
        if (lastClickCategoryId != -1){
            var lastCategory = $("#category_" + lastClickCategoryId + " .dd2-content:first");
            lastCategory.removeClass("btn-yellow");
            lastCategory.removeClass("no-hover");
        }
        var currentCategory = $("#category_" + categoryId + " .dd2-content:first");
        currentCategory.addClass("btn-yellow");
        currentCategory.addClass("no-hover");
        lastClickCategoryId = categoryId;

        loadProductList(categoryId);
    }

    // 渲染图片列表
    function loadProductImgList() {
        $.ajax({
            url : "/product/productImg.json",
            type : "post",
            success : function (result) {
                if (result.status == "success"){
                    // 将每一张图片添加到map中
                    $.each(result.data, function (i, img) {
                        productImgMap[img.id] = img;
                        // alert(img.imgName);
                    });
                }
            }
        });
    }

    // 渲染商品列表
    function loadProductList(categoryId) {
        loadProductImgList();
        // 获取页码和当前页显示条数
        pageNo = $('#showProductPage .pageNo').val() || 1;
        pageSize = $('#pageSize').val();
        keyword = $('#keyword').val(); // 获取关键字
        search_status = $('#search_status').val(); // 获取状态
        var url = "/product/product.json?categoryId=" + categoryId; // 请求资源
        $.ajax({
            url: url,
            type: "POST",
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                keyword : keyword,
                search_status : search_status
            },
            success : function (result) {
                renderProductListAndPage(result, url);
            }
        });
    }

    // 渲染细节
    function renderProductListAndPage(result, url) {
        if (result.status == "success"){
            if (result.data.total > 0){
                var rendered = Mustache.render(productShowTemplate, {
                    "showProductList" : result.data.records,
                    "showProductImg" : function () {
                        return productImgMap[this.imgId].imgName;
                    }
                });
                // 将product添加到map中
                $.each(result.data.records, function (i, product) {
                    productMap[product.id] = product;
                });
                //在指定id下添加带数据的页面样式
                $('#showProductList').html(rendered);
            }else {
                $('#showProductList').html("");
            }
            bindProductDetailsClick();
            pageNo = $('#showProductPage .pageNo').val() || 1; // 获取当前页码
            pageSize = $('#pageSize').val(); // 获取显示条数
            // 分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records.length : 0,
                "showProductPage",
                renderProductListAndPage,
                true);
        }else {
            showMessage("获取分类下的商品列表",result.msg, false);
        }
    }

    // 商品详情
    function bindProductDetailsClick() {

        $('.productItem').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            // 获取商品id
            var productId = $(this).attr("data-id");

            // 弹出框
            $("#dialog-details-form").dialog({
                model : true,
                title : "商品详情",
                open : function (event, ui) {
                    $(".ui-dialog").css("width", "350px");//增加模态框的宽高
                    // 清空表单
                    $('#productDetails')[0].reset();
                    // 拿到键值对
                    var targetProDt = productMap[productId];
                    // alert(productImgMap[targetProDt.imgId].imgName);
                    if (targetProDt){
                        $('#showImg').attr("src", "/img/"+ productImgMap[targetProDt.imgId].imgName);
                        $('#price').html(targetProDt.price);
                        $('#productName').html(targetProDt.productName);
                        $('#describes').html(targetProDt.describes);
                    }
                }

            });
            bindAddCart(productId);
        });
    }

    function bindAddCart(productId) {

        $("#cart-add").click(function () {
            var number = $("#number").val();
            $.ajax({
                url:"/shoppingcart/addCart.json",
                type: "POST",
                data: {
                    number: number,
                    productId:productId
                },
                success: function (result) {
                    if (result.status == "success"){
                        $('#dialog-details-form').dialog("close");
                        showMessage("加入购物车",result.msg,true);
                    }else {
                        $('#dialog-details-form').dialog("close");
                        showMessage("加入购物车",result.msg,false);
                    }

                }
            });
        });
    }


});