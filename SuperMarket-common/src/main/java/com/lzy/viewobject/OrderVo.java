package com.lzy.viewobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVo { // 订单

    private Integer id; // 订单号

    private String address; // 地址

    private String remark; // 备注

    private Double price; // 价格

    private Date createTime; // 创建时间

    private String payWay; // 付款方式

    private Date payTime; // 付款时间

    private Date sendTime; // 发货时间

    private Date takeTime; // 收货时间

    private Integer orderStatus; // 状态

    private String telephone; // 联系方式



}
