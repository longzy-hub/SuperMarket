<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/30 0030
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-details-form" style="display: none">
    <form id="productDetails">
        <div class="thumbnail"  data-id={{id}}>
            <img id="showImg" />
            <p>商品价格： ￥<span class="text-danger" id="price"></span>元</p>
            <p>商品名称：<span id="productName"></span></p>
            <p>商品描述：<span id="describes"></span></p>
            <p>购买数量：<input id="number" type="number" value="1"/></p>
            <p class="center"><a id="cart-add" href="#" class="btn btn-xs btn-danger" role="button">
                <span class="menu-icon fa fa-shopping-cart" style="font-size: 16px"></span>
                加入购物车
            </a></p>
        </div>
    </form>
</div>