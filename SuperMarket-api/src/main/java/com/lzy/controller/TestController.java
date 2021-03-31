package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.mapper.SysRoleMapper;
import com.lzy.pojo.SysRole;
import com.lzy.response.CommonReturnType;
import com.lzy.utils.ImageUtils;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.TestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

//controller + ResponseBody
//@RestController
@Controller
public class TestController {

    @Resource
    private ValidatorApi validator;

    @Resource
    private SysRoleMapper sysRoleMapper;

    // 返回用户页面
    @RequestMapping("/imageUpload")
    public void userPage(@RequestParam("fileName")MultipartFile fileName) throws IOException {

        String name = ImageUtils.uploadImg(fileName);
        System.out.println(name);
    }
    // 返回用户页面
    @RequestMapping("/image")
    public String userPage() {
        return   "Test";
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public CommonReturnType save(){
        SysRole entity = SysRole.builder().name("guanliyuan").describes("ssss")
                .status(1).creator("admin").createTime(new Date())
                .updateTime(new Date()).build();
        sysRoleMapper.insert(entity);
        return CommonReturnType.success("success");
    }

    // 测试分页
    @RequestMapping("/page.json")
    @ResponseBody
    public CommonReturnType page(){
        IPage<SysRole> page = new Page<>(1,2);
        page = sysRoleMapper.selectPage(page, null);
        System.out.println(sysRoleMapper.selectCount(null));
        return CommonReturnType.success(page);
    }


    @RequestMapping("/query.json")
    @ResponseBody
    public CommonReturnType query(){
        SysRole role = sysRoleMapper.selectById(1);
        return CommonReturnType.success(role);
    }

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
