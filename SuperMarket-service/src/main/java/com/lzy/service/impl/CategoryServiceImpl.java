package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lzy.dto.SearchCategoryDto;
import com.lzy.error.BusinessException;
import com.lzy.mapper.CategoryMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Category;
import com.lzy.service.CategoryService;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.CategoryVo;
import com.lzy.viewobject.SearchCategoryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private UserServiceImpl userService;

    // 返回分类列表
    @Override
    public IPage<Category> categoryListAndPage(SearchCategoryVo categoryVo, PageQuery pageQuery) {
        // 注解检验
        validator.check(pageQuery);
        // 将vo转换为dto
        SearchCategoryDto dto = new SearchCategoryDto();
        if (StringUtils.isNotBlank(categoryVo.getCategory_name())){
            dto.setCategory_name(categoryVo.getCategory_name());
        }
        if (StringUtils.isNotBlank(categoryVo.getSearch_status())){
            dto.setSearch_status(Integer.parseInt(categoryVo.getSearch_status()));
        }
        // 查询条件
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getCategory_name())){
            queryWrapper.like("name", dto.getCategory_name());
        }
        if (StringUtils.isNotBlank(String.valueOf(dto.getSearch_status()))){
            queryWrapper.eq("category_status", dto.getSearch_status());
        }
        // 统计符合条件的数据
        int count = categoryMapper.selectCount(queryWrapper);
        IPage<Category> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (count > 0){
            page = categoryMapper.selectPage(page, queryWrapper.orderByDesc("id"));
            return page;
        }
        return page;
    }

    // 添加分类
    @Override
    public void insertCategory(CategoryVo categoryVo) {
        // 注解检验
        validator.check(categoryVo);
        // 分类去重
        if (checkCategoryNameExist(categoryVo.getName(), categoryVo.getId())){
            throw new BusinessException("分类已经存在！");
        }
        // 建造者模式
        Category entity = Category.builder().name(categoryVo.getName()).categoryStatus(categoryVo.getCategoryStatus())
                .describes(categoryVo.getDescribes()).creator(userService.getPrincipal()).createTime(new Date())
                .updateTime(new Date()).build();
        // 添加分类
        categoryMapper.insert(entity);
    }

    // 更新分类
    @Override
    public void updateCategory(CategoryVo categoryVo) {
        // 注解检验
        validator.check(categoryVo);
        Category before = categoryMapper.selectById(categoryVo.getId());
        Preconditions.checkNotNull(before, "待更新的分类不存在");
        // 分类去重
        if (checkCategoryNameExist(categoryVo.getName(), categoryVo.getId())){
            throw new BusinessException("分类已经存在！");
        }
        // 建造者模式
        Category entity = Category.builder().id(categoryVo.getId()).name(categoryVo.getName())
                .categoryStatus(categoryVo.getCategoryStatus()).describes(categoryVo.getDescribes())
                .creator(userService.getPrincipal()).updateTime(new Date()).build();

        // 更新角色
        categoryMapper.updateById(entity);

    }

    // 单个删除分类
    @Override
    public void delCategory(CategoryVo categoryVo) {
        categoryMapper.deleteById(categoryVo.getId());
    }

    // 批量删除分类
    @Override
    public void batchDelCategory(int[] ids) {
        if (null !=ids && ids.length > 0){
            for (int id : ids){
                categoryMapper.deleteById(id);
            }
        }
    }

    // 分类去重
    private boolean checkCategoryNameExist(String name, Integer id) {
//        System.out.println(id+ "-------------------");
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        if (null != id){
            queryWrapper.ne("id", id);
        }
        return categoryMapper.selectCount(queryWrapper) > 0;
    }
}
