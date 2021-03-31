<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/23 0023
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-user-form" style="display: none;">
    <form id="userForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="roleSelectId">所属角色</label></td>
                <td><select id="roleSelectId" name="roleId"
                            data-placeholder="选择角色" style="width: 150px;"></select></td>
            </tr>
            <tr>
                <td><label for="userName" style="width: 60px">用户名</label></td>
                <td>
                    <input type="text" name="username" id="userName" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="userId"/>
                </td>
            </tr>
            <tr>
                <td><label for="userTelephone">电话</label></td>
                <td><input type="text" name="telephone" id="userTelephone"
                           value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userMail">邮箱</label></td>
                <td><input type="text" name="mail" id="userMail" value=""
                           class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userStatus">状态</label></td>
                <td>
                    <select id="userStatus" name="userStatus" data-placeholder="状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
