<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/29 0029
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="productShowTemplate" type="x-tmpl-mustache">
<div class="row">
    {{#showProductList}}
        <div class="col-xs-2">
            <div class="thumbnail productItem"  style="height: 225px; cursor:pointer;" data-id={{id}}>
                <img src="/img/{{showProductImg}}" />
                <span class="text-danger" style="font-size : 20px">￥{{price}}</span>
                <h5>{{productName}}</h5>
                    <%--<p style=" word-wrap: break-word">{{describes}}</p>
                    <a href="#" class="btn btn-xs btn-warning" role="button">
                        <span class="menu-icon fa fa-shopping-cart" style=" color: coral;font-size: 16px"></span>
                        加入购物车
                    </a>--%>
            </div>
        </div>
    {{/showProductList}}
</div>
</script>