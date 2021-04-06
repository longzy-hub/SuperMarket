package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.mapper.PersonalMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Personal;
import com.lzy.service.PersonalService;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.PersonalVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private PersonalMapper personalMapper;

    // 返回登录信息列表
    @Override
    public IPage<Personal> personalListAndPage(Integer userId, PageQuery query) {
        validator.check(query);
        // 查询条件
        QueryWrapper<Personal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int count = personalMapper.selectCount(queryWrapper);
        IPage<Personal> page = new Page<>(query.getPageNo(), query.getPageSize());
        if (count > 0){
            page = personalMapper.selectPage(page,queryWrapper.orderByDesc("id"));
            return page;
        }
        return page;
    }

    // 登录成功插入
    @Override
    public void insertPersonal(Integer id) {
        Personal entity = Personal.builder().userId(id).status(1).time(new Date()).build();
        personalMapper.insert(entity);
    }

}
