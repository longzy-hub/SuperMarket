$(function () {

    // 批量选择
    $(".batchDel-th").click(function(){
        var checks=$(".batchDel-check");
        $.each(checks,function(i,input){
            //状态反选
            input.checked=input.checked==true?false:true;
        });
    });

    // 批量删除
    $('.batchDel-btn').click(function () {
        var ids = [];
        // 拿到当前被选中的数据
        var checks = $('.batchDel-check:checked');
        if (checks.length != null && checks.length > 0){
            // 拿到被选中的数据的id
            $.each(checks, function (i, check) {
                var id = $(check).closest("tr").attr("data-id");
                ids.push(id);
                alert(id)
            });

            $.ajax({
                url : "/product/batchDel.json",
                type : "POST",
                traditional : true,
                data : {
                    ids : ids
                },
                success : function (result) {
                    loadProductList(lastClickCategoryId);
                }
            });
            // ids = [];
        }
    });

    var productImgMap = {}; // 图片
    var categoryList; // 存储商品分类列表
    var categoryMap = {}; // 存储map格式的分类信息
    var productMap = {}; // 存储map格式的商品信息
    var pageNo; // 当前页码
    var pageSize;   // 每页显示条数
    var keyword;    // 关键字查询
    var search_status; // 状态
    var optionStr = ""; // 分类标签
    var lastClickCategoryId = -1; // 最后一次选中分类id

    // 使用mustache模板
    var  categoryListTemplate2 = $('#categoryListTemplate2').html();
    Mustache.parse(categoryListTemplate2);
    // 商品的mustache模板
    var productListTemplate = $('#productListTemplate').html();
    Mustache.parse(productListTemplate);

    loadCategoryList();

    // 查询操作
    $('.research').click(function (e) {
        // 阻止默认事件
        e.preventDefault();
        $("#productPage .pageNo").val(1);
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
        // 渲染商品列表
        loadProductList(categoryId);
    }

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
        pageNo = $('#productPage .pageNo').val() || 1;
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
                var rendered = Mustache.render(productListTemplate, {
                    "productList" : result.data.records,
                    "showCategory" : function () {
                        return categoryMap[this.categoryId].name;
                    },
                    "showProductImg" : function () {
                        return productImgMap[this.imgId].imgName;
                    },
                    "create_time" : function () {
                        return function (text, render){
                            return new Date(
                                this.createTime)
                                .Format("yyyy-MM-dd");
                        }
                    },
                    "update_time" : function() {
                        return function(text, render) {
                            return new Date(
                                this.updateTime)
                                .Format("yyyy-MM-dd");
                        }
                    },
                    "showStatus" : function() {
                        return this.productStatus == 1 ? '有效'
                            : (this.productStatus == 0 ? '无效'
                                : '删除');
                    },
                    "bold" : function() {
                        return function(text, render) {
                            var status = render(text);
                            if (status == '有效') {
                                return "<span class='label label-sm label-success'>有效</span>";
                            } else if (status == '无效') {
                                return "<span class='label label-sm label-warning'>无效</span>";
                            } else {
                                return "<span class='label'>删除</span>";
                            }
                        }
                    }
                });
                // 将product添加到map中
                $.each(result.data.records, function (i, product) {
                    productMap[product.id] = product;
                });
                //在指定id下添加带数据的页面样式
                $('#productList').html(rendered);
            }else {
                $('#productList').html("");
            }
            // 绑定操作
            bindProductClick();
            pageNo = $('#productPage .pageNo').val() || 1; // 获取当前页码
            pageSize = $('#pageSize').val(); // 获取显示条数
            // 分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records.length : 0,
                "productPage",
                renderProductListAndPage,
                true);
        }else {
            //showMessage("获取分类下的商品列表",result.msg, false);
        }
    }

    function opcategory(){
        // 分类选择
        optionStr = "";
        if (categoryList && categoryList.length > 0){
            $.each(categoryList, function (i, category) {
                optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {id: category.id, name:  category.name});
            });
        }
        $('#categorySelectId').html(optionStr);
    }

    // 添加商品
    $('.product-add').click(function () {
        // 弹出框
        $('#dialog-product-form').dialog({
            model : true, // 背景不可点击
            title : "添加商品",
            open : function (event, ui) {
                $(".ui-dialog").css("width", "350px");//增加模态框的宽高
                // 隐藏关闭按钮
                $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                opcategory();
                // 清空表单
                $('#productForm')[0].reset();
            },
            buttons : {
                "添加" : function (e) {
                    // 阻止默认事件
                    e.preventDefault();
                    // 执行添加分类
                    updateProduct(true, function (data) {
                        // 添加成功后提示信息
                        showMessage("添加商品", data.msg, true);
                        // 关闭模态框
                        $('#dialog-product-form').dialog("close");
                        loadProductList(lastClickCategoryId);
                    }, function (data) {
                        // 添加失败后提示信息
                        showMessage("添加商品", data.msg,false);
                        $('#dialog-product-form').dialog("close");
                    });
                },
                "取消" : function () {
                    $('#dialog-product-form').dialog("close");
                }
            }
        });
    });

    // 绑定操作
    function bindProductClick() {
        // 更新商品
        $('.product-edit').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取商品id
            var productId = $(this).attr('data-id');
            // 弹出修改框
            $('#dialog-product-form').dialog({
                model : true,
                title: "更新商品",
                open: function (event, ui) {
                    $(".ui-dialog").css("width", "350px");//增加模态框的宽高
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    opcategory();
                    // 清空表单
                    $('#productForm')[0].reset();
                    //  拿到map中的键值对
                    var targetProduct = productMap[productId];
                    // 如果存在，则取出
                    if (targetProduct){
                        $('#categorySelectId').val(targetProduct.categoryId);
                        $('#productId').val(targetProduct.id);
                        $('#productName').val(targetProduct.productName);
                        $('#productPrice').val(targetProduct.price);
                        //$('#productImage').val(targetProduct.ImgId);
                        $('#productDescribes').val(targetProduct.describes);
                    }
                },
                buttons: {
                    "更新" : function (e) {
                        // 阻止默认事件
                        e.preventDefault();
                        // 更新商品
                        updateProduct(false, function (data) {
                            // 更新成功后提示信息
                            showMessage("更新商品", data.msg, true);
                            $('#dialog-product-form').dialog("close");
                            $('#productPage .pageNo').val(1);
                            loadProductList(lastClickCategoryId);
                        }, function (data) {
                            showMessage("更新商品", data.msg, false);
                        });
                    },
                    "取消" : function () {
                        $('#dialog-product-form').dialog("close");
                    }
                }
            });
        });

        // 删除商品
        $('.product-del').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            // 获取商品id
            var productId = $(this).attr('data-id');
            // 获取商品名称
            var productName = $(this).attr('data-name');
            // 弹出是否删除商品
            if (confirm("确定要删除商品[" + productName + "]吗？")){
                $.ajax({
                    url : "/product/del.json",
                    type : "POST",
                    data : {
                        id : productId
                    },
                    success : function (result) {
                        // 数据执行成功返回的消息
                        if (result.status == "success"){
                            showMessage("删除商品[" + productName + "]", result.msg, true);
                            loadProductList(lastClickCategoryId);
                        }else {
                            showMessage("删除商品[" + productName + "]", result.msg, false);
                        }
                    }
                });
            }
        });
    }

    // 执行添加更新操作
    function updateProduct(isCreate, successCallback, failCallback) {
        var formData = new FormData($('#productForm')[0]);
        $.ajax({
            url : isCreate ? "/product/insert.json" : "/product/update.json",
            type : "POST",
            data : formData,
            contentType : false,
            processData : false,
            success : function (result) {
                // 数据执行成功返回的消息
                if (result.status == "success"){
                    // 带参回调函数
                    loadProductList();
                    if (successCallback){
                        successCallback(result);
                    }
                }else {
                    // 执行失败返回的消息
                    if (failCallback){
                        failCallback(result);
                    }
                }
            }
        });
    }



});
