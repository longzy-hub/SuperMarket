<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/2 0002
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-personal-form" style="display: none;">
    <form id="personalForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td><label for="userName" style="width: 60px">用户名</label></td>
                <td>
                    <input type="text" name="username" id="userName" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="userId"/>
                </td>
            </tr>
            <tr>
                <td><label for="userTelephone">手机号</label></td>
                <td><input type="text" name="telephone" id="userTelephone"
                           value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userMail">邮箱</label></td>
                <td><input type="text" name="mail" id="userMail" value=""
                           class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userPassword">密码</label></td>
                <td><input type="text" name="password" id="userPassword"
                           placeholder="若需修改密码，请输入新密码" style="width: 200px"
                           value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
        </table>
    </form>
</div>
