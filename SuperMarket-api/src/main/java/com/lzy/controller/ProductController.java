package com.lzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.*;
import com.lzy.response.CommonReturnType;
import com.lzy.service.ProductService;
import com.lzy.viewobject.ProductVo;
import com.lzy.viewobject.SearchProductVo;
import com.lzy.viewobject.SearchUserVo;
import com.lzy.viewobject.SysUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static String FPATH = "product/";

    @Resource
    private ProductService productService;

    // 返回商品列表页面
    @RequestMapping("/product.page")
    public String productPage(){
        return FPATH + "product";
    }

    // 返回商品展示页面
    @RequestMapping("/productShow.page")
    public String productShowPage(){
        return FPATH + "productShow";
    }

    // 返回分类列表
    @RequestMapping("/categoryList.json")
    @ResponseBody
    public CommonReturnType categoryList(){
        List<Category> result = productService.categoryList();
        return CommonReturnType.success(result);
    }

    // 返回上商品列表
    @RequestMapping("/product.json")
    @ResponseBody
    public CommonReturnType user(@RequestParam("categoryId") int categoryId, SearchProductVo productVo, PageQuery pageQuery){
        IPage<Product> result = productService.productPageByCategoryId(categoryId, productVo, pageQuery);
        return  CommonReturnType.success(result);
    }

    // 获取图片
    @RequestMapping("/productImg.json")
    @ResponseBody
    public CommonReturnType img(){
        List<ProductImg> result = productService.imgList();
        return CommonReturnType.success(result);
    }


    // 添加商品
    @RequestMapping("/insert.json")
    @ResponseBody
    public CommonReturnType insert(ProductVo productVo) throws IOException {
        productService.insertProduct(productVo);
        return CommonReturnType.success("success");
    }

    // 更新商品
    @RequestMapping("/update.json")
    @ResponseBody
    public CommonReturnType update(ProductVo productVo) throws IOException {
        productService.updateProduct(productVo);
        return CommonReturnType.success("success");
    }

    // 单个删除商品
    @RequestMapping("/del.json")
    @ResponseBody
    public CommonReturnType del(ProductVo productVo){
        productService.delProduct(productVo);
        return CommonReturnType.success("success");
    }
    
    // 批量删除
    @RequestMapping("/batchDel.json")
    @ResponseBody
    public CommonReturnType batchDel(@RequestParam("ids") int[] ids){
        productService.batchDelProduct(ids);
        return CommonReturnType.success("success");
    }

}
