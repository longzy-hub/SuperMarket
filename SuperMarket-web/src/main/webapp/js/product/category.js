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
            });
            // $.each(selectedCategoryId, function (i, id) {
            //     alert(id);
            // });
            $.ajax({
                url : "/category/batchDel.json",
                type : "POST",
                traditional : true,
                data : {
                    ids : ids
                },
                success : function (result) {
                    loadCategoryList();
                }
            });
           // ids = [];
        }
    });


    var categoryMap = {}; // 存储map格式的分类列表信息
    var url; // 资源请求url
    var pageSize; // 页面显示条数
    var pageNo; // 当前页码
    var category_name; // 名称
    var search_status; // 查询状态


    // 加入模板内容进入HTML中
    // 将页面样式，赋给一个变量
    var categoryListTemplate = $("#categoryListTemplate").html();
    // 将变量加载到mustache解析引擎中，往引擎中填充数据（渲染视图）
    Mustache.parse(categoryListTemplate);

    // 渲染分页分类列表
    loadCategoryList();

    // 点击刷新
    $('.research').click(function(e){
        // 阻止默认事件
        e.preventDefault();
        $("#categoryPage .pageNo").val(1);
        loadCategoryList();
    });

    // 渲染分类列表细节
    function loadCategoryList(newurl) {
        pageSize = $("#pageSize").val(); // 获取页面显示条数
        pageNo = $("#categoryPage .pageNo").val() || 1; // 获取当前页码
        // 判断是否传进来了url
        if (newurl){
            url = newurl;
        }else{
            url = "/category/category.json";
        }
        category_name = $("#category_name").val(); // 获取页面上的分类名称查询
        search_status = $("#search_status").val(); // 获取状态
        // alert(search_status);
        // 发送请求
        $.ajax({
            url : url,
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                category_name : category_name,
                search_status : search_status
            },
            type : 'POST',
            success : function (result) {
                // 渲染指定页面下列表
                renderCategoryListAndPage(result, url);
            }
        });
    }

    // 渲染mustache模板页面，指定页面下的分类列表
    function renderCategoryListAndPage(result, url) {
        // result是从数据库返回过来的数据集合
        if (result.status == 'success') {
            // 再次初始化一下查询条件（可以省略）
            url = "/category/category.json";
            category_name = $("#category_name").val(); // 获取页面上的分类名称查询
            search_status = $("#search_status").val(); // 获取状态
            // 如果数据库中有符合条件的数据
            if (result.data.total > 0){
                // 视图渲染
                var rendered = Mustache.render(categoryListTemplate,
                    {
                        "categoryList" : result.data.records,
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
                            return this.categoryStatus == 1 ? '有效'
                                : (this.categoryStatus == 0 ? '无效'
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
                // each 增强for,遍历每一条数据处理，
                $.each(result.data.records, function(i, category) {//java-增强for
                    categoryMap[category.id] = category;
                });
                // 在指定id下，填充带数据的页面样式
                $('#categoryList').html(rendered);
            }else {
                $('#categoryList').html("");
            }
            // 绑定操作
            bindCategoryClick();
            pageSize = $("#pageSize").val(); // 获取页面显示条数
            pageNo = $("#categoryPage .pageNo").val() || 1; // 获取当前页码
            // 渲染分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records .length : 0,
                "categoryPage",
                renderCategoryListAndPage,
                true);
        }else {
            showMessage("获取分类列表", result.msg, false);
        }
    }

    // 添加分类
    $('.category-add').click(function () {
        // 弹出框
        $('#dialog-category-form').dialog({
            model : true, // 背景不可点击
            title : "添加分类",
            open : function (event, ui) {
                // 隐藏关闭按钮
                $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                // 清空表单
                $('#categoryForm')[0].reset();
            },
            buttons : {
                "添加" : function (e) {
                    // 阻止默认事件
                    e.preventDefault();
                    // 执行添加分类
                    updateCategory(true, function (data) {
                        // 添加成功后提示信息
                        showMessage("添加分类", data.msg, true);
                        // 关闭模态框
                        $('#dialog-category-form').dialog("close");
                        loadCategoryList();
                    }, function (data) {
                        // 添加失败后提示信息
                        showMessage("添加分类", data.msg,false);
                        $('#dialog-category-form').dialog("close");
                    });
                },
                "取消" : function () {
                    $('#dialog-category-form').dialog("close");
                }
            }
        });
    });

    // 绑定操作
    function bindCategoryClick() {
        // 更新分类
        $('.category-edit').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取分类id
            var categoryId = $(this).attr('data-id');
            // 弹出修改框
            $('#dialog-category-form').dialog({
                model : true,
                title: "更新分类",
                open: function (event, ui) {
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    // 清空表单
                    $('#categoryForm')[0].reset();
                    //  拿到map中的键值对
                    var targetCategory = categoryMap[categoryId];
                    // 如果存在，则取出
                    if (targetCategory){
                        $('#categoryId').val(targetCategory.id);
                        $('#categoryName').val(targetCategory.name);
                        $('#categoryStatus').val(targetCategory.categoryStatus);
                        $('#categoryDescribes').val(targetCategory.describes);
                    }
                },
                buttons: {
                    "更新" : function (e) {
                        // 阻止默认事件
                        e.preventDefault();
                        // 更新分类
                        updateCategory(false, function (data) {
                            // 更新成功后提示信息
                            showMessage("更新分类", data.msg, true);
                            $('#dialog-category-form').dialog("close");
                            $('#categoryPage .pageNo').val(1);
                            loadCategoryList();
                        }, function (data) {
                            showMessage("更新分类", data.msg, false);
                        });
                    },
                    "取消" : function () {
                        $('#dialog-category-form').dialog("close");
                    }
                }
            });
        });

        // 删除分类
        $('.category-del').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            // 获取分类id
            var categoryId = $(this).attr('data-id');
            // 获取分类名称
            var categoryName = $(this).attr('data-name');
            // 弹出是否删除分类
            if (confirm("确定要删除分类[" + categoryName + "]吗？")){
                $.ajax({
                    url : "/category/del.json",
                    type : "POST",
                    data : {
                        id : categoryId
                    },
                    success : function (result) {
                        // 数据执行成功返回的消息
                        if (result.status == "success"){
                            showMessage("删除分类[" + categoryName + "]", result.msg, true);
                            loadCategoryList();
                        }else {
                            showMessage("删除分类[" + categoryName + "]", result.msg, false);
                        }
                    }
                });
            }
        });
    }

    // 执行添加更新操作
    function updateCategory(isCreate, successCallback, failCallback) {
        $.ajax({
            url : isCreate ? "/category/insert.json" : "/category/update.json",
            type : "POST",
            data : $('#categoryForm').serializeArray(), // 数据序列化
            success : function (result) {
                // 数据执行成功返回的消息
                if (result.status == "success"){
                    // 带参回调函数
                    loadCategoryList();
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