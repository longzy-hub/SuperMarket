package com.lzy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller + ResponseBody
@RestController
public class TestController {

    // 指定接收一个get请求
    @GetMapping("/test")
    public Object test(){
        return "success";
    }

}
