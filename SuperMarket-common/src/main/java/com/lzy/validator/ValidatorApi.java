package com.lzy.validator;

import com.lzy.error.BusinessException;
import org.springframework.beans.factory.InitializingBean;

// 校验器接口api
public interface ValidatorApi extends InitializingBean {
    public void check(Object bean) throws BusinessException;
}
