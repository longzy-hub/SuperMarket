<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/3 0003
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="toBeDeliveredListTemplate" type="x-tmpl-mustache">
{{#toBeDeliveredList}}
 <tr role="row" class="order-name odd" data-id="{{id}}"><!--even -->
     <td>{{id}}</td>
     <td>{{telephone}}</td>
     <td>{{address}}&nbsp;&nbsp;{{remark}}</td>
     <td>{{price}}</td>
     <td>{{#pay_time}}{{/pay_time}}</td>
     <td>{{#bold}}{{showStatus}}{{/bold}}</td>
     <td>
        <div class="hidden-sm hidden-xs action-buttons">
            <a class="btn btn-primary btn-xs see-order" data-id="{{id}}">
               查看订单详细
            </a>
        </div>
     </td>
 </tr>
{{/toBeDeliveredList}}
</script>
