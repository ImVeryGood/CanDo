package com.component.app.user.bean;

/**
 * date:2019/6/10
 * describe：
 */
public class LoginBean {

    /**
     * data : null
     * errorCode : -1
     * errorMsg : 账号或者密码长度必须大于6位！
     */

    private Object data;
    private int errorCode;
    private String errorMsg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
