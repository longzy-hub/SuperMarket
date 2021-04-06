$(function () {

    var num = 0; // 总价格
    var backList;
    var optionStr = "";
    var cartMap = {};
    var cartIds = [];
    var cartProductIds = [];
    var cartNumbers = [];

    // 使用mustache模板技术
    var cartListTemplate = $('#cartListTemplate').html();
    Mustache.parse(cartListTemplate);

    loadShoppingCartList();

    function loadShoppingCartList() {
        $.ajax({
            url : "/shoppingcart/shopping.json",
            type : "POST",
            success : function (result) {
                if (result.status == "success"){
                    if (result.data.length > 0){
                        var rendered = Mustache.render(cartListTemplate, {
                            "cartList" : result.data,
                        });
                        $.each(result.data, function (i,cart) {
                            // cartMap[cart.id] = cart;
                            cartIds.push(cart.id);
                            cartProductIds.push(cart.productId);
                            cartNumbers.push(cart.number);
                            num = num + cart.price*cart.number;
                            // alert(cart.price*cart.number);
                        });
                        $("#cartList").html(rendered);
                    }else {
                        $("#cartList").html("");
                    }
                    bindClickCart();
                    // 显示总价格
                    $('#pay-money').html(num);
                    var pay_money = $('#pay-money').html();
                    if (pay_money >= 30){
                        $('#pay').removeClass("disabled");
                    }else {
                        $('#pay').addClass("disabled");
                    }
                }else {
                    showMessage("获取购物车列表", result.msg, false);
                }
            }
        });
    }

    // 查询所有配送地址
    loadBackList();

    function loadBackList(){
        $.ajax({
            url: "/back/backList.json",
            type: "POST",
            success : function (result) {
                if (result.status == "success"){
                    backList = result.data;
                }
            }
        })
    }

    // 付款
    $("#pay").click(function () {

        // $.each(cartIds, function (i,cart) {
        //     alert(cartIds[i])
        // });

        // 弹出框
        $("#dialog-cart-form").dialog({
            model: true,
            title: "立即购买",
            open: function (event,ui) {
                // $(".ui-dialog").css("width", "350px");//增加模态框的宽高
                // 清空表单
                $('#cart')[0].reset();
                // 填充数据
                $("#price").html(num);
                optionStr = "";
                if (backList && backList.length > 0){
                    $.each(backList, function (i, back) {
                        optionStr += Mustache.render("<option id='{{id}}' " +
                            "value='{{address}}'>{{address}}</option>",
                            {id: back.id, address:  back.address});
                    });
                }
                $("#cartAddress").html(optionStr);
                // 点击购买
                $('#buy').click(function (e) {
                    // 阻止默认事件动作
                    e.preventDefault();
                    var price = $('#price').html();
                    var cartAddress = $('#cartAddress').val();
                    var remark = $("#remark").val();
                    // alert(price + cartAddress + remark)
                    $.ajax({
                        url : "/shoppingcart/markOrder.json",
                        type: "POST",
                        traditional : true,
                        data : {
                            price : price,
                            cartAddress : cartAddress,
                            remark : remark,
                            cartIds : cartIds,
                            cartNumbers: cartNumbers,
                            cartProductIds : cartProductIds
                        },
                        success : function (result) {
                            if (result.status == "success"){
                                $('#dialog-cart-form').dialog("close");
                                loadShoppingCartList();
                                num = 0;
                                showMessage("付款",result.msg,true);
                            }else {
                                $('#dialog-cart-form').dialog("close");
                                showMessage("付款",result.msg,false);
                            }
                        }

                    });


                });
            }
        });
    });

    function bindClickCart() {

        $('.p-del').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();
            // 获取id
            var cartId = $(this).attr('data-id');
            //var targetCart = cartMap[cartId];
            updateCart(true,cartId);
        });

        $('.p-add').click(function (e) {
            // 阻止默认事件冒泡
            e.preventDefault();
            e.stopPropagation();

            // 获取id
            var cartId = $(this).attr('data-id');
            updateCart(false,cartId);

        });
    }

    function updateCart(isCreate, cartId) {
        $.ajax({
            url: isCreate ? "/shoppingcart/updateNumber.json" : "/shoppingcart/updateAddNumber.json",
            type: "POST",
            data:{
                id: cartId
            },
            success: function (result) {
                if (result.status == "success"){
                    num = 0;
                    loadShoppingCartList();
                }
            }
        });
    }




});