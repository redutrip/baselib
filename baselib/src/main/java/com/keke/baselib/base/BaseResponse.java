package com.keke.baselib.base;

/**
 * Created by Administrator on 2016/4/25.
 */
public class BaseResponse{

    private String returnCode;
    private String message;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
