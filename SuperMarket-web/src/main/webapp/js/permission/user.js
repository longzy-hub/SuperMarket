$(function () {

    var roleList; // 存储角色列表
    var roleMap = {}; // 存储map格式的角色信息
    var userMap = {}; // 存储map格式的用户信息
    var pageNo; // 当前页码
    var pageSize;   // 每页显示条数
    var keyword;    // 关键字查询
    var search_status; // 状态
    var optionStr = ""; // 角色标签
    var lastClickRoleId = -1; // 最后一次选中角色id

    // 使用mustache模板
    var  roleListTemplate2 = $('#roleListTemplate2').html();
    Mustache.parse(roleListTemplate2);
    // 用户的mustache模板
    var userListTemplate = $('#userListTemplate').html();
    Mustache.parse(userListTemplate);

    loadRoleList();

    // 查询操作
    $('.research').click(function (e) {
        // 阻止默认事件
        e.preventDefault();
        $("#userPage .pageNo").val(1);
        loadUserList(lastClickRoleId);
    });

    // 渲染角色列表
    function loadRoleList() {
        $.ajax({
            url : "/sys/permission/roleList.json",
            type : "POST",
            success : function (result) {
                // 如果返回成功
                if (result.status == "success"){
                    // 渲染视图
                    var rendered = Mustache.render(roleListTemplate2 , {
                        "roleList" : result.data
                    });
                    roleList = result.data;
                    // 将每一个角色添加到map中
                    $.each(result.data, function (i, role) {
                        roleMap[role.id] = role;
                    });
                    // 在指定id下填充数据页面样式
                    $('#roleList').html(rendered);
                    // 绑定操作
                    bindRoleClick();
                }else {
                    showMessage("加载角色列表", result.msg, false);
                }
            }
        });
    }

    // 绑定角色操作
    function bindRoleClick() {

        $('.role-name').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取角色id
            var roleId = $(this).attr("data-id");
            handleRoleSelected(roleId);
        });
    }

    // 点击角色后的变化
    function  handleRoleSelected(roleId) {
        if (lastClickRoleId != -1){
            var lastRole = $("#role_" + lastClickRoleId + " .dd2-content:first");
            lastRole.removeClass("btn-yellow");
            lastRole.removeClass("no-hover");
        }
        var currentRole = $("#role_" + roleId + " .dd2-content:first");
        currentRole.addClass("btn-yellow");
        currentRole.addClass("no-hover");
        lastClickRoleId = roleId;
        // 渲染用户列表
        loadUserList(roleId);
    }

    // 渲染用户列表
    function loadUserList(roleId) {

        // 获取页码和当前页显示条数
        pageNo = $('#userPage .pageNo').val() || 1;
        pageSize = $('#pageSize').val();
        keyword = $('#keyword').val(); // 获取关键字
        search_status = $('#search_status').val(); // 获取状态
        var url = "/sys/permission/user.json?roleId=" + roleId; // 用户请求资源
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
                renderUserListAndPage(result, url);
            }
        });
    }

    // 渲染细节
    function renderUserListAndPage(result, url) {
        if (result.status == "success"){
            if (result.data.total > 0){
                var rendered = Mustache.render(userListTemplate, {
                    "userList" : result.data.records,
                    "showRoleId" : function () {
                        return roleMap[this.roleId].name;
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
                        return this.userStatus == 1 ? '有效'
                            : (this.userStatus == 0 ? '无效'
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
                // 将user添加到usermap中
                $.each(result.data.records, function (i, user) {
                    userMap[user.id] = user;
                });
                //在指定id下添加带数据的页面样式
                $('#userList').html(rendered);
            }else {
                $('#userList').html("");
            }

            // 绑定操作
            bindUserClick();
            pageNo = $('#userPage .pageNo').val() || 1; // 获取当前页码
            pageSize = $('#pageSize').val(); // 获取显示条数
            // 分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records.length : 0,
                "userPage",
                renderUserListAndPage);
        }else {
            showMessage("获取角色下的用户列表",result.msg, false);
        }

    }

    // 绑定用户操作
    function bindUserClick() {
        // 更新用户
        $('.user-edit').click(function (e) {
            // 阻止事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取用户id
            var userId = $(this).attr("data-id");
            // 弹出框
            $('#dialog-user-form').dialog({
                model : true, // 背景不可修改
                title : "更新用户",
                open : function (event, ui) {
                    // 隐藏关闭按钮
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    // 角色选择
                    optionStr = "";
                    if (roleList && roleList.length > 0){
                        $.each(roleList, function (i, role) {
                            optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {id: role.id, name:  role.name});
                        });
                    }
                    // 清空表单
                    $('#userForm')[0].reset();
                    $('#roleSelectId').html(optionStr);
                    // 拿到map中的键值对
                    var targetUser = userMap[userId];
                    // 如果存在，则取出
                    if (targetUser){
                        $('#roleSelectId').val(targetUser.roleId);
                        $('#userId').val(targetUser.id);
                        $('#userName').val(targetUser.username)
                        $('#userTelephone').val(targetUser.telephone);
                        $('#userMail').val(targetUser.mail);
                        $('#userStatus').val(targetUser.userStatus);
                    }
                },
                buttons : {
                    "更新" : function (e) {
                        e.preventDefault();
                        // 更新用户
                        $.ajax({
                            url : "/sys/permission/updateUser.json",
                            type : "POST",
                            data : $('#userForm').serializeArray(), // 数据序列化
                            success : function (result) {
                                if(result.status == "success"){
                                    showMessage("更新角色",result.msg, true);
                                    // 关闭模态框
                                    $('#dialog-user-form').dialog("close");
                                    // 显示用户列表
                                    loadUserList(lastClickRoleId);
                                }else {
                                    showMessage("更新角色",result.msg, false);
                                }
                            }
                        });
                    },
                    "取消" : function () {
                        $('#dialog-user-form').dialog("close");
                    }
                }
            });
        });
    }


});
