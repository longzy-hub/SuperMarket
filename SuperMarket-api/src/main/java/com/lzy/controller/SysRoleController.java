package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysRole;
import com.lzy.response.CommonReturnType;
import com.lzy.service.SysRoleService;
import com.lzy.viewobject.SearchRoleVo;
import com.lzy.viewobject.SysRoleVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/permission")
public class SysRoleController {

    public static String FPATH = "permission/";

    @Resource
    private SysRoleService sysRoleService;

    // 返回页面
    @RequestMapping("/role.page")
    public String RolePage(){
        return FPATH + "role";
    }

    // 获取角色列表（读取数据）
    @RequestMapping("/role.json")
    @ResponseBody
    public CommonReturnType searchPage(SearchRoleVo roleVo, PageQuery pageQuery){
        IPage<SysRole> result = (IPage<SysRole>) sysRoleService.searchRoleAndPage(roleVo,pageQuery);
        return CommonReturnType.success(result);
    }

    // 添加角色
    @RequestMapping("/insert.json")
    @ResponseBody
    public CommonReturnType insert(SysRoleVo roleVo){
//        System.out.println(roleVo.getId());
//        System.out.println(roleVo.getName());
        sysRoleService.insertRole(roleVo);
        return CommonReturnType.success("success");
    }

    // 修改角色
    @RequestMapping("/update.json")
    @ResponseBody
    public CommonReturnType update(SysRoleVo roleVo){
//        System.out.println(roleVo.getId());
        sysRoleService.updateRole(roleVo);
        return CommonReturnType.success("success");
    }

    // 删除角色
    @RequestMapping("/del.json")
    @ResponseBody
    public CommonReturnType del(SysRoleVo roleVo){
        sysRoleService.delRole(roleVo);
        return CommonReturnType.success("success");
    }


    // 返回角色列表
    @RequestMapping("/roleList.json")
    @ResponseBody
    public CommonReturnType roleList(){
        List<SysRole> result = sysRoleService.roleList();
        return CommonReturnType.success(result);
    }


}
