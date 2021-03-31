<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/26 0026
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-product-form" style="display: none;">
    <form id="productForm" enctype="multipart/form-data">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" >
            <tr>
                <td style="width: 80px;"><label for="categorySelectId">所属分类</label></td>
                <td><select id="categorySelectId" name="categoryId"
                            data-placeholder="选择分类" style="width: 150px;"></select></td>
            </tr>
            <tr>
                <td><label for="productName" style="width: 60px">商品名称</label></td>
                <td>
                    <input type="text" name="productName" id="productName" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="productId"/>
                </td>
            </tr>
            <tr>
                <td><label for="productPrice">价格（元）</label></td>
                <td><input type="text" name="price" id="productPrice"
                           value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="productImage">商品图片</label></td>
                <td><input type="file" name="productImg" id="productImage" value=""
                           class="text ui-widget-content ui-corner-all" style="width: 200px"></td>
            </tr>
            <tr>
                <td><label for="productStatus">状态</label></td>
                <td>
                    <select id="productStatus" name="productStatus" data-placeholder="状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="productDescribes">描述</label></td>
                <td><textarea id="productDescribes" name="describes" class="text ui-widget-content ui-corner-all" rows="3" cols="20" ></textarea></td>
            </tr>
        </table>
    </form>
</div>
