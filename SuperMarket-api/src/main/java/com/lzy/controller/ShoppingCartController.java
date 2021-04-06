package com.lzy.controller;

import com.lzy.pojo.OrderItem;
import com.lzy.pojo.ShoppingCart;
import com.lzy.response.CommonReturnType;
import com.lzy.service.ShoppingCartService;
import com.lzy.viewobject.CartVo;
import com.lzy.viewobject.ShoppingCartVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private static String FPATH = "order/";

    @Resource
    private ShoppingCartService shoppingService;

    @RequestMapping("/cart.page")
    public String cartPage(){
        return FPATH + "shoppingCart";
    }

    // 显示购物车
    @RequestMapping("/shopping.json")
    @ResponseBody
    public CommonReturnType shopping(){
        List<ShoppingCartVo> result = shoppingService.searchShoppingByUser();
        return CommonReturnType.success(result);
    }

    // 加入购物车
    @RequestMapping("/addCart.json")
    @ResponseBody
    public CommonReturnType addCart(ShoppingCart shoppingCart){
//        System.out.println(shoppingCart.getProductId());
        shoppingService.addCart(shoppingCart);
        return CommonReturnType.success("success");
    }

    // 删除商品数量
    @RequestMapping("/updateNumber.json")
    @ResponseBody
    public CommonReturnType updateNumber(@RequestParam("id") Integer cartId){
        shoppingService.updateNumberAndPage(cartId);
        return CommonReturnType.success("success");
    }

    // 添加商品数量
    @RequestMapping("/updateAddNumber.json")
    @ResponseBody
    public CommonReturnType updateAddNumber(@RequestParam("id") Integer cartId){
        shoppingService.updateAddNumberAndPage(cartId);
        return CommonReturnType.success("success");
    }

    // 立即付款
    @RequestMapping("/markOrder.json")
    @ResponseBody
    public CommonReturnType make(CartVo cartVo) throws ParseException {

        shoppingService.makeOrder(cartVo);
        return CommonReturnType.success("success");
    }


}
