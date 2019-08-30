package com.hysyyl.bloodapp.activity.login

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.LoginResult
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

/**
 * @desc:登录界面model
 * @date:2019/8/15 20:29
 * @auther:lp
 * @version:1.1.6
 */
class LoginModel {
    /**
     * 登录
     */
    fun login(phone: String, password: String, mode: Int, callBack: HttpRequestCallBackKT<LoginResult>) {
        HttpUtils.getInstance().login(phone, password, mode, callBack)
    }

    /**
     * 发送验证码
     */
    fun getCode(phone: String, callBack: HttpRequestCallBackKT<String>) {
        HttpUtils.getInstance().sendCode(phone, callBack)
    }
}