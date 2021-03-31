package com.lzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Category;
import com.lzy.pojo.Product;
import com.lzy.pojo.ProductImg;
import com.lzy.viewobject.ProductVo;
import com.lzy.viewobject.SearchProductVo;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Category> categoryList();

    IPage<Product> productPageByCategoryId(int categoryId, SearchProductVo productVo, PageQuery pageQuery);

    void insertProduct(ProductVo productVo) throws IOException;

    void updateProduct(ProductVo productVo) throws IOException;

    List<ProductImg> imgList();

    void delProduct(ProductVo productVo);

    void batchDelProduct(int[] ids);
}
