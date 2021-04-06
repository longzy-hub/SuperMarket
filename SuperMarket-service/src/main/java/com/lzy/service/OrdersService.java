package com.lzy.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.viewobject.OiAndProVO;
import com.lzy.viewobject.OrderVo;

import java.util.List;

public interface OrdersService {

    List<OiAndProVO> orderList(Integer orderId);

    void delOrder(Integer orderId);

    void paid(Integer orderId);

    void takeOrder(Integer orderId);

    IPage<OrderVo> getOrderListAndPage(Integer search_status, PageQuery query);

    IPage<OrderVo> getDealOrderListAndPage(Integer search_status, PageQuery query);

    void sendOrder(Integer orderId);

    Double countMoney();

    int countOrder();
}
