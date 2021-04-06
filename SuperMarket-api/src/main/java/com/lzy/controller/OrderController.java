package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Orders;
import com.lzy.response.CommonReturnType;
import com.lzy.service.OrdersService;
import com.lzy.viewobject.OiAndProVO;
import com.lzy.viewobject.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static String FPATH = "order/";

    @Resource
    private OrdersService ordersService;

    // 返回已完成页面
    @RequestMapping("/completed.page")
    public String completedPage(){
        return FPATH + "completed";
    }

    // 返回待付款页面
    @RequestMapping("/toBePaid.page")
    public String toBePaidPage(){
        return FPATH + "toBePaid";
    }

    // 返回待发货页面
    @RequestMapping("/toBeDelivered.page")
    public String toBeDeliveredPage(){
        return FPATH + "toBeDelivered";
    }

    // 返回待收货页面
    @RequestMapping("/toBeReceived.page")
    public String toBeReceivedPage(){
        return FPATH + "toBeReceived";
    }

    // 处理订单
    @RequestMapping("/dealOrder.page")
    public String dealOrderPage(){
        return FPATH + "dealOrder";
    }

    // 处理订单
    @RequestMapping("/handledOrder.page")
    public String handledOrderPage(){
        return FPATH + "handledOrder";
    }

    // 显示订单列表
    @RequestMapping("/handleOrder.json")
    @ResponseBody
    public CommonReturnType handle(@RequestParam("search_status") Integer search_status,PageQuery query){
        IPage<OrderVo> result = ordersService.getOrderListAndPage(search_status,query);
        return CommonReturnType.success(result);
    }

    // 显示订单项详情
    @RequestMapping("/orderList.json")
    @ResponseBody
    public CommonReturnType orderList(@RequestParam("orderId") Integer orderId){
        List<OiAndProVO> result = ordersService.orderList(orderId);
        return CommonReturnType.success(result);
    }

    // 取消订单
    @RequestMapping("/delOrder.json")
    @ResponseBody
    public CommonReturnType delOrder(@RequestParam("orderId") Integer orderId){
        ordersService.delOrder(orderId);
        return CommonReturnType.success("success");
    }

    // TODO
    // 付款
    @RequestMapping("/paid.json")
    @ResponseBody
    public CommonReturnType paid(@RequestParam("orderId") Integer orderId){
        ordersService.paid(orderId);
        return CommonReturnType.success("success");
    }

    // 去收货
    @RequestMapping("/takeOrder.json")
    @ResponseBody
    public CommonReturnType takeOrder(@RequestParam("orderId") Integer orderId){
        ordersService.takeOrder(orderId);
        return CommonReturnType.success("success");
    }

    // 处理订单
    @RequestMapping("/order.json")
    @ResponseBody
    public CommonReturnType dealOrder(@RequestParam("search_status") Integer search_status,PageQuery query){
        IPage<OrderVo> result = ordersService.getDealOrderListAndPage(search_status,query);
        return CommonReturnType.success(result);
    }

    // 发货
    @RequestMapping("/sendOrder.json")
    @ResponseBody
    public CommonReturnType sendOrder(@RequestParam("orderId") Integer orderId){
        ordersService.sendOrder(orderId);
        return CommonReturnType.success("success");
    }


}
