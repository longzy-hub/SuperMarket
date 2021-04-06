package com.lzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Back;
import com.lzy.viewobject.BackVo;
import com.lzy.viewobject.SearchBackVo;

import java.util.List;

public interface BackService {

    IPage<Back> backListAndPage(SearchBackVo backVo, PageQuery query);

    void insertAddress(BackVo backVo);

    void updateAddress(BackVo backVo);

    void delAddress(BackVo backVo);

    void batchDelAddress(int[] ids);

    List<Back> backList();
}
