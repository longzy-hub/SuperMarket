package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Category;
import com.lzy.response.CommonReturnType;
import com.lzy.service.CategoryService;
import com.lzy.viewobject.CategoryVo;
import com.lzy.viewobject.SearchCategoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static String FPATH = "product/";

    @Resource
    private CategoryService categoryService;

    // 返回分页页面
    @RequestMapping("/category.page")
    public String categoryPage(){
        return FPATH + "category";
    }

    // 返回分类列表
    @RequestMapping("/category.json")
    @ResponseBody
    public CommonReturnType categoryList(SearchCategoryVo categoryVo, PageQuery pageQuery){
        IPage<Category> result = categoryService.categoryListAndPage(categoryVo, pageQuery);
        return CommonReturnType.success(result);
    }

    // 添加分类
    @RequestMapping("/insert.json")
    @ResponseBody
    public CommonReturnType insert(CategoryVo categoryVo){
        categoryService.insertCategory(categoryVo);
        return CommonReturnType.success("success");
    }

    // 更新分类
    @RequestMapping("/update.json")
    @ResponseBody
    public CommonReturnType update(CategoryVo categoryVo){
        categoryService.updateCategory(categoryVo);
        return CommonReturnType.success("success");
    }

    // 单个删除
    @RequestMapping("/del.json")
    @ResponseBody
    public CommonReturnType delete(CategoryVo categoryVo){
        categoryService.delCategory(categoryVo);
        return CommonReturnType.success("success");
    }

    // 批量删除分类
    @RequestMapping("/batchDel.json")
    @ResponseBody
    public CommonReturnType batchDel(@RequestParam("ids") int[] ids){
        categoryService.batchDelCategory(ids);
        return CommonReturnType.success("success");
    }


}
