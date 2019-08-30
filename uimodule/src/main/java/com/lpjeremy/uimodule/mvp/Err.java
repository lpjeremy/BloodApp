package com.lpjeremy.uimodule.mvp;
/**
 * @desc:加载失败，错误信息
 * @date:2019/3/6 8:57
 * @auther:lp
 * @version:1.1.6
 */
public class Err {
    /**
     * 错误编码
     */
    private int code;
    /**
     * 错误提示
     */
    private String errMsg;
    /**
     * 错误信息
     */
    private Throwable throwable;

    public Err(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public Err(int code, Throwable throwable) {
        this.code = code;
        this.throwable = throwable;
        this.errMsg = throwable != null ? throwable.getMessage() : "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
