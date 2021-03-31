package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lzy.dto.SearchRoleDto;
import com.lzy.error.BusinessException;
import com.lzy.mapper.SysRoleMapper;
import com.lzy.mapper.SysUserMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysRole;
import com.lzy.pojo.SysUser;
import com.lzy.service.SysRoleService;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.SearchRoleVo;
import com.lzy.viewobject.SysRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private UserServiceImpl userService;

    // 根据角色名称去重
    private  boolean checkRoleNameExist(String roleName, Integer id){
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", roleName);
        if (null != id){
            queryWrapper.ne("id", id);
        }
        return sysRoleMapper.selectCount(queryWrapper) > 0;
    }

    // x显示角色列表
    @Override
    public Object searchRoleAndPage(SearchRoleVo roleVo, PageQuery pageQuery) {
        // 注解检验
        validator.check(pageQuery);
        // 将vo字段传入dto中， dto---------数据库交互数据传输对象
        SearchRoleDto dto = new SearchRoleDto();
        if (StringUtils.isNotBlank(roleVo.getRole_name())){
            dto.setRole_name(roleVo.getRole_name());
        }
        if (StringUtils.isNotBlank(roleVo.getSearch_status())){
            dto.setSearch_status(Integer.parseInt(roleVo.getSearch_status()));
        }
        // 查询条件
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getRole_name())){
            queryWrapper.like("name",dto.getRole_name());
        }
        if (StringUtils.isNotBlank(String.valueOf(dto.getSearch_status()))){
            queryWrapper.eq("status", dto.getSearch_status());
        }
        // 统计符合条件的数据
        int count = sysRoleMapper.selectCount(queryWrapper);
        IPage<SysRole> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (count > 0){
            page = sysRoleMapper.selectPage(page, queryWrapper);
            return page;
        }
        return page;
    }

    // 添加角色
    @Override
    public void insertRole(SysRoleVo roleVo) {
        // 注解检验
        validator.check(roleVo);
        // 角色去重
        if (checkRoleNameExist(roleVo.getName(), roleVo.getId())){
            throw new BusinessException("角色已经存在！");
        }
//        getPrincipal();
        // 建造者模式
        SysRole entity = SysRole.builder().name(roleVo.getName()).describes(roleVo.getDescribes())
                .status(roleVo.getStatus()).creator(userService.getPrincipal())
                .createTime(new Date()).updateTime(new Date()).build();
        // 添加角色
        sysRoleMapper.insert(entity);
    }

    // 删除角色
    @Override
    public void delRole(SysRoleVo roleVo) {
        sysRoleMapper.deleteById(roleVo.getId());
    }

    // 更新角色
    @Override
    public void updateRole(SysRoleVo roleVo) {
        // 注解检验
        validator.check(roleVo);
        SysRole before = sysRoleMapper.selectById(roleVo.getId());
        Preconditions.checkNotNull(before, "待更新角色不存在");
        // 角色去重
        if (checkRoleNameExist(roleVo.getName(), roleVo.getId())){
            throw new BusinessException("角色已经存在！");
        }
        // 建造者模式
        SysRole entity = SysRole.builder().id(roleVo.getId()).name(roleVo.getName()).describes(roleVo.getDescribes())
                .status(roleVo.getStatus()).creator(userService.getPrincipal())
                .updateTime(new Date()).build();
        // 更新角色
        sysRoleMapper.updateById(entity);
    }



    @Override
    public List<SysRole> roleList() {
        List<SysRole> roles = sysRoleMapper.selectList(null);
        return roles;
    }
}
