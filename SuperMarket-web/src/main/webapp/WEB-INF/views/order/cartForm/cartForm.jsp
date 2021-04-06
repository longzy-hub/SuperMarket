<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/6 0006
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dialog-cart-form" style="display: none">
    <form id="cart">
        <div class="thumbnail"  data-id={{id}}>
            <img id="showImg" src="/img/1596554960031.jpg" width="150" />
            <p class="center red">￥&nbsp;<span class="text-danger" id="price"></span>&nbsp;元</p>
            <p>配送地址 : <select id="cartAddress" style="width: 150px;"></select></p>
            <p>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注 :
                <textarea id="remark"  placeholder="例如：4层451室"></textarea></p>
            <p class="center"><a id="buy" href="#" class="btn btn-xs btn-danger" role="button">
                立即购买
            </a></p>
        </div>
    </form>
</div>
