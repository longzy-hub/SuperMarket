package com.lzy.service;

import com.lzy.pojo.ShoppingCart;
import com.lzy.viewobject.CartVo;
import com.lzy.viewobject.ShoppingCartVo;

import java.text.ParseException;
import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCartVo> searchShoppingByUser();

    void updateNumberAndPage(Integer cartId);

    void updateAddNumberAndPage(Integer cartId);

    void addCart(ShoppingCart shoppingCart);

    void makeOrder(CartVo cartVo) throws ParseException;
}
