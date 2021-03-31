package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysPerm;
import com.lzy.response.CommonReturnType;
import com.lzy.service.SysPermService;
import com.lzy.viewobject.SysPermVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/permission")
public class SysPermController {

    public static String FPATH = "permission/";

    @Resource
    private SysPermService sysPermService;

    @RequestMapping("/perm.page")
    public String permPage(){
        return FPATH + "perm";
    }


    // 获取权限列表
    @RequestMapping("/perm.json")
    @ResponseBody
    public CommonReturnType perm(PageQuery query){
        IPage<SysPerm> result = sysPermService.permList(query);
        return CommonReturnType.success(result);
    }

    // 添加权限
    @RequestMapping("/insertperm.json")
    @ResponseBody
    public CommonReturnType insertPerm(SysPermVo permVo){
        sysPermService.insertPerm(permVo);
        return CommonReturnType.success("success");
    }

    // 修改权限
    @RequestMapping("/updateperm.json")
    @ResponseBody
    public CommonReturnType updatePerm(SysPermVo permVo){
        sysPermService.updatePerm(permVo);
        return CommonReturnType.success("success");
    }

    // 删除权限
    @RequestMapping("/delperm.json")
    @ResponseBody
    public CommonReturnType delPerm(SysPermVo permVo){
        sysPermService.delPerm(permVo);
        return CommonReturnType.success("success");
    }


}
