package com.lpjeremy.libmodule.http.model;

/**
 * @desc:请求结果基础类
 * @date:2019/1/27 11:29
 * @auther:lp
 * @version:1.0
 */
public class BaseResult<T> {

    protected boolean IsSuccess;

    protected int ErrorCode;

    protected String Msg;

    protected int Count;

    private T Data;


    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        this.IsSuccess = success;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        this.ErrorCode = errorCode;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "IsSuccess=" + IsSuccess +
                ", ErrorCode=" + ErrorCode +
                ", Msg='" + Msg + '\'' +
                ", Count=" + Count +
                ", Data=" + Data +
                '}';
    }
}
