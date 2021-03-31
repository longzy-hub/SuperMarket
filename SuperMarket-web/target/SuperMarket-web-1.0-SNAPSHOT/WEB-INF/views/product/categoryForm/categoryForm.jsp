<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/25 0025
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-category-form" style="display: none;">
    <form id="categoryForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td><label for="categoryName" style="width: 60px">分类名称</label></td>
                <td>
                    <input type="text" name="name" id="categoryName" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="categoryId"/>
                </td>
            </tr>
            <tr>
                <td><label for="categoryStatus">状态</label></td>
                <td>
                    <select id="categoryStatus" name="categoryStatus" data-placeholder="状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="categoryDescribes">描述</label></td>
                <td><textarea id="categoryDescribes" name="describes" class="text ui-widget-content ui-corner-all" rows="3" cols="20" ></textarea></td>
            </tr>
        </table>
    </form>
</div>
