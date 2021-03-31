package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysRole;
import com.lzy.pojo.SysUser;
import com.lzy.response.CommonReturnType;
import com.lzy.service.SysUserService;
import com.lzy.viewobject.SearchUserVo;
import com.lzy.viewobject.SysUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/permission")
public class SysUserController {

    public static String FPATH = "permission/";

    @Resource
    private SysUserService sysUserService;

    // 返回用户页面
    @RequestMapping("/user.page")
    public String userPage(){
        return FPATH + "user";
    }

    // 返回用户列表
    @RequestMapping("/user.json")
    @ResponseBody
    public CommonReturnType user(@RequestParam("roleId") int roleId, SearchUserVo userVo, PageQuery pageQuery){
        IPage<SysUser> result = sysUserService.userPageByRoleId(roleId, userVo, pageQuery);
        return  CommonReturnType.success(result);
    }

    // 更新用户
    @RequestMapping("/updateUser.json")
    @ResponseBody
    public CommonReturnType update(SysUserVo userVo){
        sysUserService.updateUser(userVo);
        return  CommonReturnType.success("success");
    }


}
