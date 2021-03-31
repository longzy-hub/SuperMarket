package com.lzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Category;
import com.lzy.viewobject.CategoryVo;
import com.lzy.viewobject.SearchCategoryVo;

public interface CategoryService {

    IPage<Category> categoryListAndPage(SearchCategoryVo categoryVo, PageQuery pageQuery);

    void insertCategory(CategoryVo categoryVo);

    void updateCategory(CategoryVo categoryVo);

    void delCategory(CategoryVo categoryVo);

    void batchDelCategory(int[] ids);
}
