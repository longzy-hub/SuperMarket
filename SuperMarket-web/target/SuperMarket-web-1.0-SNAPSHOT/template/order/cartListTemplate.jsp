<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/3 0003
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="cartListTemplate" type="x-tmpl-mustache">
{{#cartList}}
    <tr role="row" class="product-name odd" data-id="{{id}}"><!--even -->
       <td><img src="/img/{{imgName}}" width="80px" height="50px"/></td>
       <td>{{productName}}</td>
       <td>{{price}}</td>
       <td>
               <a class="btn btn-danger btn-sm fa fa-minus p-del" data-id="{{id}}"></a>
               <input type="text" id="number" style="width: 50px;" value="{{number}}" disabled/>
               <a class="btn btn-success btn-sm fa fa-plus p-add" data-id="{{id}}"></a>
       </td>
    </tr>
{{/cartList}}
</script>
