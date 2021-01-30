package com.lzy.controller.viewobject;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TestVo {

    @NotNull
    private Integer id;
    @NotBlank
    @Length(min = 1, max = 3, message = "名称长度在1到3个字之间")
    private String name;
}
