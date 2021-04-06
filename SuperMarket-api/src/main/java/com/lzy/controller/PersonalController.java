package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Personal;
import com.lzy.pojo.SysUser;
import com.lzy.response.CommonReturnType;
import com.lzy.service.PersonalService;
import com.lzy.service.SysUserService;
import com.lzy.viewobject.PersonalVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    private static String FPATH = "personal/";

    @Resource
    private SysUserService sysUserService;

    @Resource
    private PersonalService personalService;

    @RequestMapping("/personal.page")
    public String personalPage(){
        return FPATH + "personal";
    }

    // 返回个人信息
    @RequestMapping("/personal.json")
    @ResponseBody
    public CommonReturnType personal(){
        Subject subject = SecurityUtils.getSubject();
        SysUser user = sysUserService.findByUserName((String) subject.getPrincipal());
        return CommonReturnType.success(user);
    }

    //  返回登录信息列表
    @RequestMapping("/personalList.json")
    @ResponseBody
    public CommonReturnType personalList(@RequestParam("userId") Integer userId, PageQuery query){
        IPage<Personal> result = personalService.personalListAndPage(userId, query);
        return CommonReturnType.success(result);
    }

    // 修改个人信息
    @RequestMapping("/update.json")
    @ResponseBody
    public CommonReturnType update(PersonalVo personalVo){
        sysUserService.updatePersonal(personalVo);
        return CommonReturnType.success("success");
    }

}
