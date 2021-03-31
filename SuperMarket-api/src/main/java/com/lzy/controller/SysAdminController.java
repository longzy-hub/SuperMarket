package com.lzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/admin")
public class SysAdminController {


    // 返回admin页面
    @RequestMapping("/admin.page")
    public String adminPage(){
        return  "admin";
    }

    @RequestMapping("/index.page")
    public String indexPage(){
        return  "index";
    }



}
