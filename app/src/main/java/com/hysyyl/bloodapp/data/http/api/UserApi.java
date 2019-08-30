package com.hysyyl.bloodapp.data.http.api;


import com.hysyyl.bloodapp.model.LoginResult;
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

public interface UserApi {
    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @param loginMode
     * @param callBack
     */
    void login(String phone, String pwd, int loginMode, HttpRequestCallBackKT<LoginResult> callBack);

    void checkBindWechat(HttpRequestCallBackKT<String> callBack);

}
