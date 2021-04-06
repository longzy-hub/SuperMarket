package com.lzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Orders;
import com.lzy.viewobject.OiAndProVO;
import com.lzy.viewobject.OrderVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrdersMapper extends BaseMapper<Orders> {

    // 显示订单列表
    @Select("select o.*,u.telephone\n" +
            "from orders o \n" +
            "inner join sys_user u on u.id=o.user_id\n" +
            "and o.order_status=#{search_status} and o.user_id=#{userId}\n" +
            "ORDER BY o.id")
    IPage<OrderVo> getOrderList(IPage<OrderVo> page,Integer userId, Integer search_status);

    // 显示订单详细
    @Select("select p.product_name,oi.number\n" +
            "from orderitem oi \n" +
            "inner join product p on p.id = oi.product_id\n" +
            "inner join orders o on o.id=oi.order_id and o.id = #{orderId}")
    List<OiAndProVO> getOiAndProductList(Integer orderId);


    // 显示处理订单
    @Select("select o.*,u.telephone\n" +
            "from orders o \n" +
            "inner join sys_user u on u.id=o.user_id\n" +
            "and o.order_status=#{search_status} \n" +
            "ORDER BY o.id ")
    IPage<OrderVo> getDealOrderList(IPage<OrderVo> page, Integer search_status);
}
