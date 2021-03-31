<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/31 0031
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-perm-form" style="display: none;">
    <form id="permForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="roleSelectId">所属角色</label></td>
                <td><select id="roleSelectId" name="roleId"
                            data-placeholder="选择角色" style="width: 150px;"></select></td>
            </tr>
            <tr>
                <td><label for="permName" style="width: 60px">权限名称</label></td>
                <td>
                    <input type="text" name="name" id="permName" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="permId"/>
                </td>
            </tr>
            <tr>
                <td><label for="permStatus">状态</label></td>
                <td>
                    <select id="permStatus" name="permStatus" data-placeholder="状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="permDescribes">权限描述</label></td>
                <td><textarea id="permDescribes" name="describes" class="text ui-widget-content ui-corner-all" rows="3" cols="20" ></textarea></td>
            </tr>
        </table>
    </form>
</div>
