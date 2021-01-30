package com.lzy.validator.impl;

import cn.hutool.core.map.MapUtil;
import com.lzy.error.BusinessException;
import com.lzy.error.EmBusinessError;
import com.lzy.validator.ValidationResult;
import com.lzy.validator.ValidatorApi;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements ValidatorApi {

    private Validator validator;

    @Override
    public void check(Object bean) throws BusinessException {
        ValidationResult result = validator(bean);
        if (MapUtil.isNotEmpty(result.getErrorMsgMap())){
            if (result.isHasErrors()){
                throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,//
                         result.getErrorMsgMap().toString());
            }
        }
    }

    private ValidationResult validator(Object bean) {
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0){
            // 有错误
            result.setHasErrors(true);
            constraintViolationSet.forEach(constraintViolation -> {
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 将hibernate validator 通过工厂的初始化方法使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
