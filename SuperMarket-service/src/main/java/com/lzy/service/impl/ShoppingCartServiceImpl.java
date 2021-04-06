package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.mapper.OrderItemMapper;
import com.lzy.mapper.OrdersMapper;
import com.lzy.mapper.ShoppingCartMapper;
import com.lzy.mapper.SysUserMapper;
import com.lzy.pojo.OrderItem;
import com.lzy.pojo.Orders;
import com.lzy.pojo.ShoppingCart;
import com.lzy.pojo.SysUser;
import com.lzy.service.ShoppingCartService;
import com.lzy.viewobject.CartVo;
import com.lzy.viewobject.ShoppingCartVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    // 根据用户返回购物车
    @Override
    public List<ShoppingCartVo> searchShoppingByUser() {
        // 根据用户查询购物车中的商品
        List<ShoppingCartVo> shoppingCarts = shoppingCartMapper.getProductByUser(getUserId());
        return shoppingCarts;
    }

    // 减少数量
    @Override
    public void updateNumberAndPage(Integer cartId) {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",cartId);
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(queryWrapper);

        if (shoppingCart.getNumber() > 1){
            ShoppingCart entity = ShoppingCart.builder().id(cartId).number(shoppingCart.getNumber()-1).build();
            shoppingCartMapper.updateById(entity);
        }else {
            shoppingCartMapper.deleteById(cartId);
        }
    }

    // 添加数量
    @Override
    public void updateAddNumberAndPage(Integer cartId) {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",cartId);
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(queryWrapper);

        ShoppingCart entity = ShoppingCart.builder().id(cartId).number(shoppingCart.getNumber()+1).build();
        shoppingCartMapper.updateById(entity);
    }

    // 加入购物车
    @Override
    public void addCart(ShoppingCart shoppingCart) {
        // 判断购物车中是否有相同的商品
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", shoppingCart.getProductId());
        queryWrapper.eq("user_id",getUserId());
        ShoppingCart cart = shoppingCartMapper.selectOne(queryWrapper);
        int count = shoppingCartMapper.selectCount(queryWrapper);
        if (count > 0){
            ShoppingCart entity = ShoppingCart.builder().id(cart.getId())
                    .number(cart.getNumber() + shoppingCart.getNumber()).build();
            shoppingCartMapper.updateById(entity);
        }else {
            if (null != getUserId()){
                ShoppingCart entity = ShoppingCart.builder().userId(getUserId())
                        .productId(shoppingCart.getProductId()).number(shoppingCart.getNumber())
                        .cartStatus(0).build();

                shoppingCartMapper.insert(entity);
            }
        }
    }

    // 立即付款
    @Override
    public void makeOrder(CartVo cartVo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        Date date1 = sdf.parse(date);
        Orders order = Orders.builder().userId(getUserId()).address(cartVo.getCartAddress())
                .remark(cartVo.getRemark()).price(cartVo.getPrice()).createTime(date1).payTime(date1)
                .orderStatus(2).build();
        //TODO
        order.setPayWay("支付宝");
        // 添加订单
        ordersMapper.insert(order);
        // 判断是否生成订单
        if (null != getOrderId(date)){
            // 添加订单项
            for (int i = 0; i < cartVo.getCartIds().length; i++){
//            System.out.println(cartVo.getCartIds()[i] + "--" + cartVo.getCartProductIds()[i]
//            + "--" + cartVo.getCartNumbers()[i]);
                OrderItem orderItem = OrderItem.builder().orderId(getOrderId(date))
                        .productId(cartVo.getCartProductIds()[i]).number(cartVo.getCartNumbers()[i])
                        .build();
                // 添加订单项
                orderItemMapper.insert(orderItem);
            }
            for (int i = 0; i < cartVo.getCartIds().length; i++){
                ShoppingCart shoppingCart = ShoppingCart.builder().id(cartVo.getCartIds()[i])
                        .cartStatus(1).build();
                shoppingCartMapper.updateById(shoppingCart);
            }
        }

    }

    // 根据用户和时间查询出订单号
    private Integer getOrderId(String date) {

        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", getUserId());
        queryWrapper.eq("pay_time",date);
        Orders order = ordersMapper.selectOne(queryWrapper);
        return order.getId();
    }

    // 获取用户
    private Integer getUserId(){
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone",subject.getPrincipal());
        // 根据查询条件查询用户
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        return user.getId();
    }
}
