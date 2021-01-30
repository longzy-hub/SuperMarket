package com.lzy.error;

public enum EmBusinessError implements CommonError {
    // 通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数错误"),
    SYSTEM_ERROR(10002,"系统错误"),
    UNKNOWN_ERROR(10003, "未知错误")
    ;

    EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    // 错误码
    private int errCode;
    // 错误信息
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
