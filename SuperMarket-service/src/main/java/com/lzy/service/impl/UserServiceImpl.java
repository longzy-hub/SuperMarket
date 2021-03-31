package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.mapper.SysUserMapper;
import com.lzy.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl {

    @Resource
    public SysUserMapper sysUserMapper;

    public String getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
//        System.out.println(subject.getPrincipal());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", subject.getPrincipal());
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        System.out.println(user.getUsername());
        return user.getUsername();
    }

}
