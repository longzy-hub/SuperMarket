package com.lzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Personal;
import com.lzy.viewobject.PersonalVo;

public interface PersonalService {

    IPage<Personal> personalListAndPage(Integer userId, PageQuery query);

    void insertPersonal(Integer id);

}
