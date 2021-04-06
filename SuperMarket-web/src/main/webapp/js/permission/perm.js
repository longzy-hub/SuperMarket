$(function () {

    var roleList;
    var roleMap = {};
    var permMap = {};
    var url; // 资源请求url
    var pageSize; // 页面显示条数
    var pageNo; // 当前页码
    var optionStr="";

    //  使用mustache模板
    var permListTemplate = $("#permListTemplate").html();
    Mustache.parse(permListTemplate);

    // 获取角色
    loadRoleList();

    // 渲染列表
    loadPermList();

    // 刷新
    $('#pageSize').click(function (e) {
        // 阻止默认事件
        e.preventDefault();
        $("#permPage .pageNo").val(1);
        loadPermList();
    });

    function loadRoleList() {
        $.ajax({
            url : "/sys/permission/roleList.json",
            type : "POST",
            success : function (result) {
                // 如果返回成功
                if (result.status == "success"){
                    roleList = result.data;
                    // 将每一个角色添加到map中
                    $.each(result.data, function (i, role) {
                        roleMap[role.id] = role;
                    });
                }else {
                    showMessage("加载角色", result.msg, false);
                }
            }
        });
    }

    // 渲染细节
    function loadPermList(newurl) {
        pageSize = $("#pageSize").val(); // 获取页面显示条数
        pageNo = $("#permPage .pageNo").val() || 1; // 获取当前页码
        // alert(pageNo);
        // 判断是否传进来了url
        if (newurl){
            url = newurl;
        }else{
            url = "/sys/permission/perm.json";
        }
        // 发送请求
        $.ajax({
            url : url,
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
            },
            type : 'POST',
            success : function (result) {
                // 渲染指定页面下的列表
                renderPermListAndPage(result, url);
            }
        });
    }

    function renderPermListAndPage(result, url) {
        if (result.status == "success") {
            url = "/sys/permission/perm.json";
            // 如果数据库中有符合条件的数据
            if (result.data.total > 0){
                // 视图渲染
                var rendered = Mustache.render(permListTemplate,
                    {
                        "permList" : result.data.records,
                        "showRoleName" : function () {
                            return roleMap[this.roleId].name;
                        },
                        "showStatus" : function() {
                            return this.permStatus == 1 ? '有效'
                                : (this.permStatus == 0 ? '无效'
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
                $.each(result.data.records, function(i, perm) {//java-增强for
                    permMap[perm.id] = perm;
                });
                // 在指定id下，填充带数据的页面样式
                $('#permList').html(rendered);
            }else {
                $('#permList').html("");
            }

            // 绑定操作
            bindPermClick();
            pageSize = $("#pageSize").val(); // 获取页面显示条数
            pageNo = $("#permPage .pageNo").val() || 1; // 获取当前页码
            // 渲染分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records .length : 0,
                "permPage",
                renderPermListAndPage,
                true);
        }else {
            showMessage("获取权限列表", result.msg, false);
        }
    }

    // 添加权限
    $('.perm-add').click(function () {
        // 弹出框
        $('#dialog-perm-form').dialog({
            model : true, // 背景不可点击
            title : "添加权限",
            open : function (event, ui) {
                // 隐藏关闭按钮
                $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                opPerm();
                // 清空表单
                $('#permForm')[0].reset();
            },
            buttons : {
                "添加" : function (e) {
                    // 阻止默认事件
                    e.preventDefault();
                    // 执行添加角色
                    updatePerm(true, function (data) {
                        // 添加成功后提示信息
                        showMessage("添加权限", data.msg, true);
                        // 关闭模态框
                        $('#dialog-perm-form').dialog("close");
                        loadPermList();
                    }, function (data) {
                        // 添加失败后提示信息
                        showMessage("添加权限", data.msg,false);
                        $('#dialog-perm-form').dialog("close");
                    });
                },
                "取消" : function () {
                    $('#dialog-perm-form').dialog("close");
                }
            }
        });
    });

    // 绑定角色操作
    function bindPermClick() {
        // 编辑角色
        $('.perm-edit').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取角色id
            var permId = $(this).attr('data-id');
            // 弹出修改框
            $('#dialog-perm-form').dialog({
                model : true,
                title: "更新权限",
                open: function (event, ui) {
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    opPerm();
                    // 清空表单
                    $('#permForm')[0].reset();
                    //  拿到map中的键值对
                    var targetPerm = permMap[permId];
                    // 如果存在，则取出
                    if (targetPerm){
                        $('#roleSelectId').val(targetPerm.roleId);
                        $('#permId').val(targetPerm.id);
                        $('#permName').val(targetPerm.name);
                        $('#permStatus').val(targetPerm.permStatus);
                        $('#permDescribes').val(targetPerm.describes);
                    }
                },
                buttons: {
                    "更新" : function (e) {
                        // 阻止默认事件
                        e.preventDefault();
                        // 更新角色
                        updatePerm(false, function (data) {
                            // 更新成功后提示信息
                            showMessage("更新权限", data.msg, true);
                            $('#dialog-perm-form').dialog("close");
                            $('#permPage .pageNo').val(1);
                            loadPermList();
                        }, function (data) {
                            showMessage("更新权限", data.msg, false);
                        });
                    },
                    "取消" : function () {
                        $('#dialog-perm-form').dialog("close");
                    }
                }
            });
        });

        // 删除权限
        $('.perm-del').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            // 获取角色id
            var permId = $(this).attr('data-id');
            // 获取角色名称
            var permName = $(this).attr('data-name');
            // 弹出是否删除角色
            if (confirm("确定要删除权限[" + permName + "]吗？")){
                $.ajax({
                    url : "/sys/permission/delperm.json",
                    type : "POST",
                    data : {
                        id : permId
                    },
                    success : function (result) {
                        // 数据执行成功返回的消息
                        if (result.status == "success"){
                            showMessage("删除权限[" + permName + "]", result.msg, true);
                            loadPermList();
                        }else {
                            showMessage("删除权限[" + permName + "]", result.msg, false);
                        }
                    }
                });
            }
        });
    }

    function opPerm(){
        // 分类选择
        optionStr = "";
        if (roleList && roleList.length > 0){
            $.each(roleList, function (i, role) {
                optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {id: role.id, name:  role.name});
            });
        }
        $('#roleSelectId').html(optionStr);
    }

    // 执行添加更新操作
    function updatePerm(isCreate, successCallback, failCallback) {
        $.ajax({
            url : isCreate ? "/sys/permission/insertperm.json" : "/sys/permission/updateperm.json",
            type : "POST",
            data : $('#permForm').serializeArray(), // 数据序列化
            success : function (result) {
                // 数据执行成功返回的消息
                if (result.status == "success"){
                    // 带参回调函数
                    loadPermList();
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