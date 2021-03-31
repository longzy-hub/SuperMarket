package com.lzy.controller;

import com.lzy.pojo.SysUser;
import com.lzy.service.SysUserService;
import com.lzy.viewobject.SysUserVo;
import com.lzy.viewobject.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/login")
public class SysLoginController {

    @Resource
    private SysUserService sysUserService;

    // 登录页面
    @RequestMapping("/login.page")
    public String loginPage(){
        return "login";
    }

    // 退出返回登录页面
    @RequestMapping("/logout.page")
    public String logoutPage(){
        // 获取用户信息
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); // 登出
        return "login";
    }

    // 返回注册页面
    @RequestMapping("/register.page")
    public String registerPage(){
        return "register";
    }

    // 注册用户
    @RequestMapping("/register.json")
    public String register(UserVo userVo){
        try {
            sysUserService.registerUser(userVo);
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            return "register";
        }
    }

    // 登录用户
    @RequestMapping("/login.json")
    public String login(String telephone, String password){
        try {
            // 获取主体对象
            Subject subject = SecurityUtils.getSubject();
//            System.out.println(telephone+" "+ password);
            // 认证token
            subject.login(new UsernamePasswordToken(telephone, password));
            return "admin";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "login";
    }


}
