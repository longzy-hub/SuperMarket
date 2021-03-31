package com.lzy.service;

import com.lzy.page.PageQuery;
import com.lzy.pojo.SysRole;
import com.lzy.viewobject.SearchRoleVo;
import com.lzy.viewobject.SysRoleVo;

import java.util.List;

public interface SysRoleService {

    Object searchRoleAndPage(SearchRoleVo roleVo, PageQuery pageQuery);

    void insertRole(SysRoleVo roleVo);

    void delRole(SysRoleVo roleVo);

    void updateRole(SysRoleVo roleVo);

    List<SysRole> roleList();
}
