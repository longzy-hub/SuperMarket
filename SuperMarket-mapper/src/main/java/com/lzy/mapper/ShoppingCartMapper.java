package com.lzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.pojo.ShoppingCart;
import com.lzy.viewobject.ShoppingCartVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    @Select("select sc.id,pi.img_name,p.product_name,p.price,sc.number,sc.product_id\n" +
            "from shoppingcart sc \n" +
            "inner join product p on sc.product_id = p.id\n" +
            "inner join productimg pi on pi.id=p.img_id\n" +
            "and sc.user_id = #{id} and sc.cart_status=0 ORDER BY id DESC")
    List<ShoppingCartVo> getProductByUser(Integer id);

}