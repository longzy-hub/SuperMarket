package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lzy.mapper.SysPermMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysPerm;
import com.lzy.service.SysPermService;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.SysPermVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysPermServiceImpl implements SysPermService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private SysPermMapper sysPermMapper;

    @Resource
    private UserServiceImpl userService;

    // 返回权限列表
    @Override
    public IPage<SysPerm> permList(PageQuery query) {
        // 注解检验
        validator.check(query);
        // 统计
        int count = sysPermMapper.selectCount(null);
        IPage<SysPerm> page = new Page<>(query.getPageNo(), query.getPageSize());
        if (count > 0){
           page = sysPermMapper.selectPage(page, null);
           return page;
        }
        return page;
    }

    // 添加权限
    @Override
    public void insertPerm(SysPermVo permVo) {
        validator.check(permVo);
        // 建造者模式
        SysPerm entity = SysPerm.builder().name(permVo.getName()).roleId(permVo.getRoleId())
                .permStatus(permVo.getPermStatus()).describes(permVo.getDescribes())
                .creator(userService.getPrincipal()).createTime(new Date())
                .updateTime(new Date()).build();
        // 添加
        sysPermMapper.insert(entity);
    }

    // 修改权限
    @Override
    public void updatePerm(SysPermVo permVo) {
        validator.check(permVo);
        // 判断是否存在该权限
        SysPerm before = sysPermMapper.selectById(permVo.getId());
        Preconditions.checkNotNull(before,"待更新的权限不存在！");
        // 建造者
        SysPerm entity = SysPerm.builder().id(permVo.getId()).name(permVo.getName())
                .permStatus(permVo.getPermStatus()).roleId(permVo.getRoleId())
                .describes(permVo.getDescribes()).creator(userService.getPrincipal())
                .updateTime(new Date()).build();
        sysPermMapper.updateById(entity);
    }

    // 删除权限
    @Override
    public void delPerm(SysPermVo permVo) {
        sysPermMapper.deleteById(permVo.getId());
    }


}
