<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/2 0002
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-back-form" style="display: none;">
    <form id="backForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td><label for="address" style="width: 60px">地址</label></td>
                <td>
                    <input type="text" name="address" id="address" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="backId"/>
                </td>
            </tr>
            <tr>
                <td><label for="backStatus">状态</label></td>
                <td>
                    <select id="backStatus" name="backStatus" data-placeholder="状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>

        </table>
    </form>
</div>
