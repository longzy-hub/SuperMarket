package com.lzy.controller;

import com.lzy.response.CommonReturnType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller + ResponseBody
@RestController
public class TestController {

    // 全局异常处理测试
    @GetMapping("exceptionTest")
    public CommonReturnType testException(){
        int i = 1/0;
        return CommonReturnType.create(null, "fail");
    }

    // 指定接收一个get请求
    @GetMapping("/test")
    public Object test(){
        return "success";
    }


}
