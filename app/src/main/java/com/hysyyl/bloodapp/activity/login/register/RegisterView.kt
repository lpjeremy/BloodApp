package com.hysyyl.bloodapp.activity.login.register

import com.lpjeremy.uimodule.mvp.MvpBaseView

interface RegisterView :MvpBaseView{
    /**
     * 注册有结果
     * @param result true注册成功 false注册失败
     */
    fun onRegisterResult(result:Boolean)
}