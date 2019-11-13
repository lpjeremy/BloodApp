package com.hysyyl.bloodapp.activity.login.register

import android.text.TextUtils
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

class RegisterPresenter : BasePresenter<RegisterView>() {
    private var registerModel:RegisterModel = RegisterModel()



    fun register(userName: String,password: String){
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        if(TextUtils.isEmpty(userName)){
            mView.showCheckToast("请输入用户名")
            return
        }
        if(TextUtils.isEmpty(password)){
            mView.showCheckToast("请输入密码")
            return
        }
        mView.showLoading("注册中...")
        registerModel.register(userName,password,object : HttpRequestCallBackKT<String>{
            override fun onSuccess(result: String?) {
                mView.hideLoading()
                mView.onRegisterResult(true)
            }

            override fun onFail(e: APiExceptionKT) {
                mView.hideLoading()
                mView.onRegisterResult(false)
            }
        })
    }
}