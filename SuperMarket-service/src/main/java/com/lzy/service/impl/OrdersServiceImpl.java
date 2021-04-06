package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.mapper.OrdersMapper;
import com.lzy.mapper.SysUserMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Orders;
import com.lzy.pojo.SysUser;
import com.lzy.service.OrdersService;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.OiAndProVO;
import com.lzy.viewobject.OrderVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl  implements OrdersService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private OrdersMapper ordersMapper;

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


    // 显示订单详细
    @Override
    public List<OiAndProVO> orderList(Integer orderId) {
        return ordersMapper.getOiAndProductList(orderId);
    }

    // 取消订单
    @Override
    public void delOrder(Integer orderId) {
        ordersMapper.deleteById(orderId);
    }

    // 付款
    @Override
    public void paid(Integer orderId) {
        // TODO
        Orders entity = Orders.builder().id(orderId).payWay("微信").payTime(new Date())
                .orderStatus(2).build();
        ordersMapper.updateById(entity);
    }

    // 去收货
    @Override
    public void takeOrder(Integer orderId) {
        Orders entity = Orders.builder().id(orderId).takeTime(new Date())
                .orderStatus(4).build();
        ordersMapper.updateById(entity);
    }

    // 订单列表
    @Override
    public IPage<OrderVo> getOrderListAndPage(Integer search_status, PageQuery query) {
        validator.check(query);

        IPage<OrderVo> page = new Page<>(query.getPageNo(),query.getPageSize());

        IPage<OrderVo> orders = ordersMapper.getOrderList(page,getUserId(),search_status);
        return orders;
    }

    // 显示处理订单
    @Override
    public IPage<OrderVo> getDealOrderListAndPage(Integer search_status, PageQuery query) {
        validator.check(query);

        IPage<OrderVo> page = new Page<>(query.getPageNo(),query.getPageSize());

        IPage<OrderVo> orders = ordersMapper.getDealOrderList(page,search_status);
        return orders;
    }

    // 发货
    @Override
    public void sendOrder(Integer orderId) {
        Orders entity = Orders.builder().id(orderId).sendTime(new Date())
                .orderStatus(3).build();
        ordersMapper.updateById(entity);
    }

    // 统计金额
    @Override
    public Double countMoney() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status",4);
        List<Orders> list = ordersMapper.selectList(queryWrapper);
        Double countMoney = 0.0;
        for (Orders order : list){
            countMoney += order.getPrice();
        }
        return countMoney;
    }

    // 统计订单数量
    @Override
    public int countOrder() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status",4);
//        int countOrder = ordersMapper.selectCount(queryWrapper);
        return ordersMapper.selectCount(queryWrapper);
    }
}
