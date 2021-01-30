package com.lzy.error;

// 错误类的接口定义
public interface CommonError {
    // 错误码
    public int getErrCode();
    // 错误信息
    public String getErrMsg();
    // 设置错误信息，返回错误具体子类
    public CommonError setErrMsg(String errMsg);
}
