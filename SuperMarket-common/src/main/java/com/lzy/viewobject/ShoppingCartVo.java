package com.lzy.viewobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartVo { // 购物车返回页面的vo类

    private Integer id;

    private String imgName;

    private Integer productId;

    private String productName;

    private Double price;

    private Integer number;





}
