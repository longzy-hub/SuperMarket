package com.lzy.controller;

import com.lzy.response.CommonReturnType;
import com.lzy.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/trade")
public class TradeMoneyController {

    private static String FPATH = "order/";

    @Resource
    private OrdersService ordersService;

    @RequestMapping("/trade.page")
    public String tradePage(){
        return FPATH + "tradeMoney";
    }

    // 统计金额
    @RequestMapping("/countMoney.json")
    @ResponseBody
    public CommonReturnType countMoney(){
        Double result = ordersService.countMoney();
        return CommonReturnType.success(result);
    }

    // 统计订单数量
    @RequestMapping("/countOrder.json")
    @ResponseBody
    public CommonReturnType countOrder(){
        int result = ordersService.countOrder();
        return CommonReturnType.success(result);
    }

}
