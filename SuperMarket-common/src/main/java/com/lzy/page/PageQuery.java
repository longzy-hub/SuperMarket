package com.lzy.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

// 页码查询
@ToString
public class PageQuery {

    // 页码
    @Getter
    @Setter
    @Min(value = 1, message = "当前页码不合法")
    private Integer pageNo = 1;

    // 显示条数
    @Getter
    @Setter
    @Min(value = 1, message = "每页显示条数不合法")
    private Integer pageSize = 10;

}
