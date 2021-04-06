package com.lzy.controller;

import com.lzy.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;

@Controller
@RequestMapping("/sys/mail")
public class MailController {

    @Resource
    private MailService mailService;

    // 发送邮件
    @ResponseBody
    @RequestMapping("/sendMail.json")
    public String send(@RequestParam("mail") String mail){
        String mailCode = String.valueOf(new Random().nextInt(899999)+ 100000);
        String message = "您的注册验证码为：" + mailCode;
        try{
            mailService.sendMail(mail,"注册验证码",message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mailCode;
    }

}
