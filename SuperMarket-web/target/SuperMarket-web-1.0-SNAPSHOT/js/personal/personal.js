$(function () {

    var pageSize; // 显示条数
    var pageNo; // 当前页码
    var lastUserId = -1;
    var name;

    // 使用mustache模板
    var personalTemplate = $('#personalTemplate').html();
    Mustache.parse(personalTemplate);

    var personalListTemplate = $("#personalListTemplate").html();
    Mustache.parse(personalListTemplate);

    loadPersonal();

    $('#pageSize').click(function (e) {
        // 阻止默认事件
        e.preventDefault();
        $("#personalPage .pageNo").val(1);
        loadPerMsg(lastUserId,name);
    });

    // 渲染个人信息
    function loadPersonal() {
        $.ajax({
            url: "/personal/personal.json",
            type: "POST",
            success: function (result) {
                // 判断是否请求成功
                if (result.status == "success"){
                    // 渲染视图
                    var rendered = Mustache.render(personalTemplate,{
                        "personal" : result.data,
                        "showCreateTime" : function () {
                            return new Date(this.createTime).Format("yyyy-MM-dd");
                        }
                    });
                    lastUserId = result.data.id;
                    name = result.data.username;
                    // 在指定id下填充数据页面样式
                    $("#personal").html(rendered);
                    loadPerMsg(lastUserId, name);
                    // 绑定操作
                    bindClickPersonal(result);
                }else{
                    $("#personal").html("");
                }
            }
        });
    }

    function bindClickPersonal(result){

        $("#update-personal").click(function (e) {
            // 阻止事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 弹出框
            $("#dialog-personal-form").dialog({
                model:true,
                title:"修改个人信息",
                open:function (event, ui) {
                    // 隐藏关闭按钮
                    $('.ui-dialog-titlebar-close', $(this).parent()).hide();
                    // 清空表单
                    $("#personalForm")[0].reset();
                    var targetPersonal = result.data;
                    if (targetPersonal){
                        $("#userId").val(targetPersonal.id);
                        $("#userName").val(targetPersonal.username);
                        $("#userTelephone").val(targetPersonal.telephone);
                        $("#userMail").val(targetPersonal.mail);
                        // $("#userPassword").val(targetPersonal.password);
                    }
                },
                buttons:{
                    "更新" : function (e) {
                        e.preventDefault();
                        // 更新用户
                        $.ajax({
                            url : "/personal/update.json",
                            type : "POST",
                            data : $('#personalForm').serializeArray(), // 数据序列化
                            success : function (result) {
                                if(result.status == "success"){
                                    showMessage("更新个人信息",result.msg, true);
                                    // 关闭模态框
                                    $('#dialog-personal-form').dialog("close");
                                    loadPersonal();
                                }else {
                                    showMessage("更新个人信息",result.msg, false);
                                }
                            }
                        });
                    },
                    "取消" : function () {
                        $("#dialog-personal-form").dialog("close");
                    }
                }
            });
        });
    }


    // 渲染登录信息
    function loadPerMsg(userId) {
        pageSize = $("#pageSize").val();
        pageNo = $("#personalPage .pageNo").val() || 1;
        var url = "/personal/personalList.json?userId=" + userId;
        $.ajax({
            url: url,
            type: "POST",
            data: {
                pageNo: pageNo,
                pageSize: pageSize
            },
            success: function (result) {
                renderPersonalListAndPage(result, url);
            }
        });
    }
    
    function renderPersonalListAndPage(result, url) {
        if (result.status == "success"){
            if (result.data.total > 0){
                // 渲染视图
                var rendered = Mustache.render(personalListTemplate,{
                    "personalList" : result.data.records,
                    "showUserName": function () {
                        return name;
                    },
                    "showStatus" : function () {
                        return this.status == 1 ? "登录成功" : "登录失败";
                    },
                    "showTime" : function () {
                        return new Date(this.time).Format("yyyy-MM-dd hh:mm:ss");
                    }

                });
                $("#personalList").html(rendered);
            }else {
                $("#personalList").html("");
            }
            pageNo = $('#personalPage .pageNo').val() || 1;
            pageSize = $('#pageSize').val();
            // 分页页码
            renderPage(
                url,
                result.data.total,
                pageNo,
                pageSize,
                result.data.total > 0 ? result.data.records .length : 0,
                "personalPage",
                renderPersonalListAndPage,
                true);
        }else {
            showMessage("获取登录信息列表", result.msg, false);
        }
    }


});