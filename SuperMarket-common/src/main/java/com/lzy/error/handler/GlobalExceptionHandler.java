package com.lzy.error.handler;

import com.lzy.error.BusinessException;
import com.lzy.error.EmBusinessError;
import com.lzy.response.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 全局异常处理
@ControllerAdvice
public class GlobalExceptionHandler {
    // 日志
    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonReturnType doError(HttpServletRequest httpServletRequest,//
                                    HttpServletResponse httpServletResponse, Exception ex){
        ex.printStackTrace();
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        logger.error(responseData.toString());
        return CommonReturnType.create(responseData, "fail");
    }
}
