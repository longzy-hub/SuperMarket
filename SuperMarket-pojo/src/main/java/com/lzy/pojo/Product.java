package com.lzy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product")
public class Product {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String productName;

    private String describes;

    private Double price;

    private Integer categoryId;

    private Integer imgId;

    private Integer productStatus;

    private String creator;

    private Date createTime;

    private Date updateTime;

}