package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lzy.dto.SearchBackDto;
import com.lzy.error.BusinessException;
import com.lzy.mapper.BackMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Back;
import com.lzy.pojo.Category;
import com.lzy.service.BackService;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.BackVo;
import com.lzy.viewobject.SearchBackVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BackServiceImpl implements BackService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private BackMapper backMapper;

    @Resource
    private UserServiceImpl userService;

    // 返回地址列表
    @Override
    public IPage<Back> backListAndPage(SearchBackVo backVo, PageQuery query) {
        validator.check(query);
        // 将vo转换为dto
        SearchBackDto dto = new SearchBackDto();
        if (StringUtils.isNotBlank(backVo.getBack_name())){
            dto.setBack_name(backVo.getBack_name());
        }
        if (StringUtils.isNotBlank(backVo.getSearch_status())){
            dto.setSearch_status(Integer.parseInt(backVo.getSearch_status()));
        }
        // 查询条件
        QueryWrapper<Back> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getBack_name())){
            queryWrapper.like("address", dto.getBack_name());
        }
        if (StringUtils.isNotBlank(String.valueOf(dto.getSearch_status()))){
            queryWrapper.eq("back_status", dto.getSearch_status());
        }
        // 统计符合条件的数据
        int count = backMapper.selectCount(queryWrapper);
        IPage<Back> page = new Page<>(query.getPageNo(), query.getPageSize());
        if (count > 0){
            page = backMapper.selectPage(page, queryWrapper.orderByDesc("id"));
            return page;
        }
        return page;
    }

    // 添加地址
    @Override
    public void insertAddress(BackVo backVo) {
        // 注解检验
        validator.check(backVo);
        // 地址去重
        if (checkAddressExist(backVo.getAddress(), backVo.getId())){
            throw new BusinessException("地址已经存在！");
        }
        // 建造者模式
        Back entity = Back.builder().address(backVo.getAddress()).backStatus(backVo.getBackStatus())
                .creator(userService.getPrincipal()).createTime(new Date()).updateTime(new Date())
                .build();
        // 添加地址
        backMapper.insert(entity);
    }

    // 更新地址
    @Override
    public void updateAddress(BackVo backVo) {
        // 注解检验
        validator.check(backVo);
        Back before = backMapper.selectById(backVo.getId());
        Preconditions.checkNotNull(before, "待更新的地址不存在");
        // 地址去重
        if (checkAddressExist(backVo.getAddress(), backVo.getId())){
            throw new BusinessException("地址已经存在！");
        }
        // 建造者模式
        Back entity = Back.builder().id(backVo.getId()).address(backVo.getAddress())
                .backStatus(backVo.getBackStatus()).creator(userService.getPrincipal())
                .updateTime(new Date()).build();

        // 更新地址
        backMapper.updateById(entity);
    }

    // 单个删除地址
    @Override
    public void delAddress(BackVo backVo) {
        backMapper.deleteById(backVo.getId());
    }

    // 批量删除地址
    @Override
    public void batchDelAddress(int[] ids) {
        if (null !=ids && ids.length > 0){
            for (int id : ids){
                backMapper.deleteById(id);
            }
        }
    }

    // 查询所有地址
    @Override
    public List<Back> backList() {
        List<Back> backs = backMapper.selectList(null);
        return backs;
    }

    // 地址去重
    private boolean checkAddressExist(String name, Integer id) {
        QueryWrapper<Back> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address", name);
        if (null != id){
            queryWrapper.ne("id", id);
        }
        return backMapper.selectCount(queryWrapper) > 0;
    }
}
