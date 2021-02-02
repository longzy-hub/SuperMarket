package com.lzy.controller;

import com.lzy.controller.viewobject.TestVo;
import com.lzy.response.CommonReturnType;
import com.lzy.validator.ValidatorApi;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//controller + ResponseBody
//@RestController
@Controller
public class TestController {

    @Resource
    private ValidatorApi validator;

    // 测试页面
    @RequestMapping("/index.page")
    public String indexPage(){
        return "index";
    }

    // 测试校验器
    @GetMapping("/validateTest")
    public CommonReturnType testValidator(TestVo testVo){
        validator.check(testVo);
        return CommonReturnType.success("success");
    }

    // 全局异常处理测试
    @GetMapping("/exceptionTest")
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
