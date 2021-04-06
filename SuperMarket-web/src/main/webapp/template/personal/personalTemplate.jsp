<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/1 0001
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="personalTemplate" type="x-tmpl-mustache">
    {{#personal}}
        <h5 style="margin-top: 30px">用&nbsp;&nbsp;户&nbsp;&nbsp;名：<span>{{username}}</span></h5>
        <h5 style="margin-top: 30px">手&nbsp;&nbsp;机&nbsp;&nbsp;号：<span>{{telephone}}</span></h5>
        <h5 style="margin-top: 30px">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<span>{{mail}}</span></h5>
        <h5 style="margin-top: 30px">注册时间：<span>{{showCreateTime}}</span></h5>
        <a id="update-personal" class="btn btn-success btn-sm pull-right" style="margin-top: 20px">修改个人信息</a>
    {{/personal}}
</script>