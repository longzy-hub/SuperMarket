<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/26 0026
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="productListTemplate" type="x-tmpl-mustache">
{{#productList}}
    <tr role="row" class="product-name odd" data-id="{{id}}"><!--even -->
        <td class="center">
            <label>
                <input type="checkbox" class="ace  batchDel-check">
                <span class="lbl"></span>
            </label>
        </td>
        <td><img src="/img/{{showProductImg}}" width="80px" height="50px"/></td>
        <td>{{productName}}</td>
        <td>{{describes}}</td>
        <td>{{showCategory}}</td>
        <td>{{price}}</td>
        <td>{{#create_time}}{{/create_time}}</td>
        <td>{{#update_time}}{{/update_time}}</td>
        <td>{{#bold}}{{showStatus}}{{/bold}}</td>
        <td>
            <div class="hidden-sm hidden-xs action-buttons">
                <a class="blue product-edit" href="#" data-id="{{id}}">
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                <a class="red product-del" href="#" data-id="{{id}}" data-name="{{productName}}">
                    <i class="ace-icon fa fa-trash bigger-100"></i>
                </a>
            </div>
        </td>
    </tr>
{{/productList}}
</script>