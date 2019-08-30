package com.hysyyl.bloodapp.activity.login

import com.hysyyl.bloodapp.model.LoginResult
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.MvpBaseView

/**
 * @desc:登录界面view
 * @date:2019/8/16 16:16
 * @auther:lp
 * @version:1.1.6
 */
interface LoginView : MvpBaseView {
    /**
     * 登录成功
     */
    fun loginSuccess(userInfo: LoginResult?)

    /**
     * 登录失败
     */
    fun loginFail(failApi: APiExceptionKT)

    /**
     * 切换按钮点击状态
     * @param clickable true 可点击 false不能点击
     */
    fun changeBtnStatus(clickable: Boolean)

}