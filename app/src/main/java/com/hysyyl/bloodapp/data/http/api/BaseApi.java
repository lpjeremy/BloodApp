package com.hysyyl.bloodapp.data.http.api;


import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

public interface BaseApi {
    /**
     * 获取验证码
     *
     * @param phone
     * @param callBack
     */
    void sendCode(String phone, HttpRequestCallBackKT<String> callBack);
}
