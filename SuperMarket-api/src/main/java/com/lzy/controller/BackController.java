package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Back;
import com.lzy.response.CommonReturnType;
import com.lzy.service.BackService;
import com.lzy.viewobject.BackVo;
import com.lzy.viewobject.SearchBackVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/back")
public class BackController {

    private static String FPATH = "back/";

    @Resource
    private BackService backService;

    @RequestMapping("/back.page")
    public String backPage(){
        return FPATH + "back";
    }

    // 返回地址列表
    @RequestMapping("/back.json")
    @ResponseBody
    public CommonReturnType back(SearchBackVo backVo, PageQuery query){
        IPage<Back> result = backService.backListAndPage(backVo, query);
        return CommonReturnType.success(result);
    }

    // 添加地址
    @RequestMapping("/insert.json")
    @ResponseBody
    public CommonReturnType back(BackVo backVo){
        backService.insertAddress(backVo);
        return CommonReturnType.success("success");
    }

    // 更新地址
    @RequestMapping("/update.json")
    @ResponseBody
    public CommonReturnType update(BackVo backVo){
        backService.updateAddress(backVo);
        return CommonReturnType.success("success");
    }

    // 单个删除地址
    @RequestMapping("/del.json")
    @ResponseBody
    public CommonReturnType del(BackVo backVo){
        backService.delAddress(backVo);
        return CommonReturnType.success("success");
    }

    // 批量删除地址
    @RequestMapping("/batchDel.json")
    @ResponseBody
    public CommonReturnType batchDel(@RequestParam("ids")int[] ids){
        backService.batchDelAddress(ids);
        return CommonReturnType.success("success");
    }

    // 查询所有配送地址
    @RequestMapping("/backList.json")
    @ResponseBody
    public CommonReturnType backList(){
        List<Back> result = backService.backList();
        return CommonReturnType.success(result);
    }

}
