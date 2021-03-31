package com.lzy.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysPerm;
import com.lzy.pojo.SysRole;
import com.lzy.pojo.SysUser;
import com.lzy.viewobject.SearchUserVo;
import com.lzy.viewobject.SysUserVo;
import com.lzy.viewobject.UserVo;

import java.util.List;

public interface SysUserService {

    IPage<SysUser> userPageByRoleId(int roleId, SearchUserVo userVo, PageQuery pageQuery);

    void updateUser(SysUserVo userVo);

    void registerUser(UserVo userVo);

    SysUser findByUserName(String principal);

    SysRole findRolesByUserName(String primaryPrincipal);

    List<SysPerm> findPermsByRoleId(Integer id);
}
