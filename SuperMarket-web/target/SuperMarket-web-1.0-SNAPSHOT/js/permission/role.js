$(function () {

    // // 批量选择
    // $(".batchDel-th").click(function(){
    //     var checks=$(".batchDel-check");
    //     $.each(checks,function(i,input){
    //         //状态反选
    //         input.checked=input.checked==true?false:true;
    //     });
    // });

    var roleMap = {}; // 存储map格式的角色列表信息
    var url; // 资源请求url
    var pageSize; // 页面显示条数
    var pageNo; // 当前页码
    var role_name; // 角色名称
    var search_status; // 查询状态


    // 加入模板内容进入HTML中
    // 将页面样式，赋给一个变量
    var roleListTemplate = $("#roleListTemplate").html();
    // 将变量加载到mustache解析引擎中，往引擎中填充数据（渲染视图）
    Mustache.parse(roleListTemplate);

    // 渲染分页角色列表
    loadRoleList();

    // 点击刷新
    $('.research').click(function(e){
        // 阻止默认事件
        e.preventDefault();
        $("#rolePage .pageNo").val(1);
        loadRoleList();
    });

    // 渲染角色列表细节
    function loadRoleList(newurl) {
        pageSize = $("#pageSize").val(); // 获取页面显示条数
        pageNo = $("#rolePage .pageNo").val() || 1; // 获取当前页码
        // alert(pageNo);
        // 判断是否传进来了url
        if (newurl){
            url = newurl;
        }else{
            url = "/sys/permission/role.json";
        }
        role_name = $("#role_name").val(); // 获取页面上的角色名称查询
        search_status = $("#search_status").val(); // 获取状态
        // alert(search_status);
        // 发送请求
        $.ajax({
            url : url,
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                role_name : role_name,
                search_status : search_status
            },
            type : 'POST',
            success : function (result) {
                // 渲染指定页面下的列表
                renderRoleListAndPage(result, url);
            }
        });
    }

    // 渲染mustache模板页面，指定页面下的列表
    function renderRoleListAndPage(result, url) {
        // result是从数据库返回过来的数据集合
        if (result.status == "success") {
            // 再次初始化一下查询条件（可以省略）
            url = "/sys/permission/role.json";
            role_name = $("#role_name").val(); // 获取页面上的角色名称查询
            search_status = $("#search_status").val(); // 获取状态
            // 如果数据库中有符合条件的数据
            if (result.data.total > 0){
                // 视图渲染
                var rendered = Mustache.render(roleListTemplate,
                    {
                        "roleList" : result.data.records,
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
                            return this.status == 1 ? '有效'
                                : (this.status == 0 ? '无效'
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
                $.each(result.data.records, function(i, role) {//java-增强for
                    roleMap[role.id] = role;
                });
                // 在指定id下，填充带数据的页面样式
                $('#roleList').html(rendered);
            }else {
                $('#roleList').html("");
            }

            // 绑定操作
            bindRoleClick();
            pageSize = $("#pageSize").val(); // 获取页面显示条数
            pageNo = $("#rolePage .pageNo").val() || 1; // 获取当前页码
            // 渲染分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records .length : 0,
                "rolePage",
                renderRoleListAndPage,
                true);
        }else {
            showMessage("获取角色列表", result.msg, false);
        }
    }

    // 添加角色
    $('.role-add').click(function () {
        // 弹出框
        $('#dialog-role-form').dialog({
            model : true, // 背景不可点击
            title : "添加角色",
            open : function (event, ui) {
                // 隐藏关闭按钮
                $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                // 清空表单
                $('#roleForm')[0].reset();
            },
            buttons : {
                "添加" : function (e) {
                    // 阻止默认事件
                    e.preventDefault();
                    // 执行添加角色
                    updateRole(true, function (data) {
                        // 添加成功后提示信息
                        showMessage("添加角色", data.msg, true);
                        // 关闭模态框
                        $('#dialog-role-form').dialog("close");
                        loadRoleList();
                    }, function (data) {
                        // 添加失败后提示信息
                        showMessage("添加角色", data.msg,false);
                        $('#dialog-role-form').dialog("close");
                    });
                },
                "取消" : function () {
                    $('#dialog-role-form').dialog("close");
                }
            }
        });
    });

    // 绑定角色操作
    function bindRoleClick() {
        // 编辑角色
        $('.role-edit').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取角色id
            var roleId = $(this).attr('data-id');
            // 弹出修改框
            $('#dialog-role-form').dialog({
                model : true,
                title: "更新角色",
                open: function (event, ui) {
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    // 清空表单
                    $('#roleForm')[0].reset();
                    //  拿到map中的键值对
                    var targetRole = roleMap[roleId];
                    // 如果存在，则取出
                    if (targetRole){
                        $('#roleId').val(targetRole.id);
                        $('#roleName').val(targetRole.name);
                        $('#roleStatus').val(targetRole.status);
                        $('#roleDescribes').val(targetRole.describes);
                    }
                },
                buttons: {
                    "更新" : function (e) {
                        // 阻止默认事件
                        e.preventDefault();
                        // 更新角色
                        updateRole(false, function (data) {
                            // 更新成功后提示信息
                            showMessage("更新角色", data.msg, true);
                            $('#dialog-role-form').dialog("close");
                            $('#rolePage .pageNo').val(1);
                            loadRoleList();
                        }, function (data) {
                            showMessage("更新角色", data.msg, false);
                        });
                    },
                    "取消" : function () {
                        $('#dialog-role-form').dialog("close");
                    }
                }
            });
        });

        // 删除角色
        $('.role-del').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            // 获取角色id
            var roleId = $(this).attr('data-id');
            // 获取角色名称
            var roleName = $(this).attr('data-name');
            // 弹出是否删除角色
            if (confirm("确定要删除角色[" + roleName + "]吗？")){
                $.ajax({
                    url : "/sys/permission/del.json",
                    type : "POST",
                    data : {
                        id : roleId
                    },
                    success : function (result) {
                        // 数据执行成功返回的消息
                        if (result.status == "success"){
                            showMessage("删除角色[" + roleName + "]", result.msg, true);
                            loadRoleList();
                        }else {
                            showMessage("删除角色[" + roleName + "]", result.msg, false);
                        }
                    }
                });
            }
        });
    }

    // 执行添加更新操作
    function updateRole(isCreate, successCallback, failCallback) {
        $.ajax({
            url : isCreate ? "/sys/permission/insert.json" : "/sys/permission/update.json",
            type : "POST",
            data : $('#roleForm').serializeArray(), // 数据序列化
            success : function (result) {
                // 数据执行成功返回的消息
                if (result.status == "success"){
                    // 带参回调函数
                    loadRoleList();
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