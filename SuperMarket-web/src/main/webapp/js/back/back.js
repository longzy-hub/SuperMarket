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
                url : "/back/batchDel.json",
                type : "POST",
                traditional : true,
                data : {
                    ids : ids
                },
                success : function (result) {
                    loadBackList();
                }
            });
            // ids = [];
        }
    });


    var backMap = {}; // 存储map格式的地址列表信息
    var url; // 资源请求url
    var pageSize; // 页面显示条数
    var pageNo; // 当前页码
    var back_name; // 地址
    var search_status; // 查询状态


    // 加入模板内容进入HTML中
    // 将页面样式，赋给一个变量
    var backListTemplate = $("#backListTemplate").html();
    // 将变量加载到mustache解析引擎中，往引擎中填充数据（渲染视图）
    Mustache.parse(backListTemplate);

    // 渲染配送地址列表
    loadBackList();

    // 点击刷新
    $('.research').click(function(e){
        // 阻止默认事件
        e.preventDefault();
        $("#backPage .pageNo").val(1);
        loadBackList();
    });

    // 渲染地址列表细节
    function loadBackList(newurl) {
        pageSize = $("#pageSize").val(); // 获取页面显示条数
        pageNo = $("#backPage .pageNo").val() || 1; // 获取当前页码
        // 判断是否传进来了url
        if (newurl){
            url = newurl;
        }else{
            url = "/back/back.json";
        }
        back_name = $("#back_name").val(); // 获取页面上的地址查询
        search_status = $("#search_status").val(); // 获取状态
        // alert(search_status);
        // 发送请求
        $.ajax({
            url : url,
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                back_name : back_name,
                search_status : search_status
            },
            type : 'POST',
            success : function (result) {
                // 渲染指定页面下列表
                renderBackListAndPage(result, url);
            }
        });
    }

    // 渲染mustache模板页面，指定页面下的地址列表
    function renderBackListAndPage(result, url) {
        // result是从数据库返回过来的数据集合
        if (result.status == 'success') {
            // 再次初始化一下查询条件（可以省略）
            url = "/back/back.json";
            back_name = $("#back_name").val(); // 获取页面上的地址名称查询
            search_status = $("#search_status").val(); // 获取状态
            // 如果数据库中有符合条件的数据
            if (result.data.total > 0){
                // 视图渲染
                var rendered = Mustache.render(backListTemplate,
                    {
                        "backList" : result.data.records,
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
                            return this.backStatus == 1 ? '有效'
                                : (this.backStatus == 0 ? '无效'
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
                $.each(result.data.records, function(i, back) {//java-增强for
                    backMap[back.id] = back;
                });
                // 在指定id下，填充带数据的页面样式
                $('#backList').html(rendered);
            }else {
                $('#backList').html("");
            }
            // 绑定操作
            bindBackClick();
            pageSize = $("#pageSize").val(); // 获取页面显示条数
            pageNo = $("#backPage .pageNo").val() || 1; // 获取当前页码
            // 渲染分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records .length : 0,
                "backPage",
                renderBackListAndPage,
                true);
        }else {
            showMessage("获取配送地址列表", result.msg, false);
        }
    }

    // 添加
    $('.back-add').click(function () {
        // 弹出框
        $('#dialog-back-form').dialog({
            model : true, // 背景不可点击
            title : "添加配送地址",
            open : function (event, ui) {
                // 隐藏关闭按钮
                $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                // 清空表单
                $('#backForm')[0].reset();
            },
            buttons : {
                "添加" : function (e) {
                    // 阻止默认事件
                    e.preventDefault();
                    // 执行添加地址
                    updateBack(true, function (data) {
                        // 添加成功后提示信息
                        showMessage("添加配送地址", data.msg, true);
                        // 关闭模态框
                        $('#dialog-back-form').dialog("close");
                        loadBackList();
                    }, function (data) {
                        // 添加失败后提示信息
                        showMessage("添加配送地址", data.msg,false);
                        $('#dialog-back-form').dialog("close");
                    });
                },
                "取消" : function () {
                    $('#dialog-back-form').dialog("close");
                }
            }
        });
    });

    // 绑定操作
    function bindBackClick() {
        // 更新地址
        $('.back-edit').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取地址id
            var backId = $(this).attr('data-id');
            // 弹出修改框
            $('#dialog-back-form').dialog({
                model : true,
                title: "更新配送地址",
                open: function (event, ui) {
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    // 清空表单
                    $('#backForm')[0].reset();
                    //  拿到map中的键值对
                    var targetBack = backMap[backId];
                    // 如果存在，则取出
                    if (targetBack){
                        $('#backId').val(targetBack.id);
                        $('#address').val(targetBack.address);
                        $('#backStatus').val(targetBack.backStatus);
                    }
                },
                buttons: {
                    "更新" : function (e) {
                        // 阻止默认事件
                        e.preventDefault();
                        // 更新地址
                        updateBack(false, function (data) {
                            // 更新成功后提示信息
                            showMessage("更新配送地址", data.msg, true);
                            $('#dialog-back-form').dialog("close");
                            $('#backPage .pageNo').val(1);
                            loadBackList();
                        }, function (data) {
                            showMessage("更新配送地址", data.msg, false);
                        });
                    },
                    "取消" : function () {
                        $('#dialog-back-form').dialog("close");
                    }
                }
            });
        });

        // 删除地址
        $('.back-del').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            // 获取地址id
            var backId = $(this).attr('data-id');
            // 获取地址
            var address = $(this).attr('data-name');
            // 弹出是否删除地址
            if (confirm("确定要删除删除配送地址[" + address + "]吗？")){
                $.ajax({
                    url : "/back/del.json",
                    type : "POST",
                    data : {
                        id : backId
                    },
                    success : function (result) {
                        // 数据执行成功返回的消息
                        if (result.status == "success"){
                            showMessage("删除配送地址[" + address + "]", result.msg, true);
                            loadBackList();
                        }else {
                            showMessage("删除配送地址[" + address + "]", result.msg, false);
                        }
                    }
                });
            }
        });
    }

    // 执行添加更新操作
    function updateBack(isCreate, successCallback, failCallback) {
        $.ajax({
            url : isCreate ? "/back/insert.json" : "/back/update.json",
            type : "POST",
            data : $('#backForm').serializeArray(), // 数据序列化
            success : function (result) {
                // 数据执行成功返回的消息
                if (result.status == "success"){
                    // 带参回调函数
                    loadBackList();
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