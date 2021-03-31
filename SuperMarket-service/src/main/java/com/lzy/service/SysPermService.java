package com.lzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.SysPerm;
import com.lzy.viewobject.SysPermVo;

public interface SysPermService {

    IPage<SysPerm> permList(PageQuery query);

    void insertPerm(SysPermVo permVo);

    void updatePerm(SysPermVo permVo);

    void delPerm(SysPermVo permVo);
}
