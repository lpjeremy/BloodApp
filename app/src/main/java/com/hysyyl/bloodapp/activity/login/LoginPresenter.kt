package com.hysyyl.bloodapp.activity.login

import com.blankj.utilcode.util.SPUtils
import com.hysyyl.bloodapp.model.LoginResult
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

/**
 * @desc:登录界面Presenter
 * @date:2019/8/16 16:16
 * @auther:lp
 * @version:1.1.6
 */
class LoginPresenter : BasePresenter<LoginView>() {

    private var loginMode: LoginModel = LoginModel()

    /**
     * 登录
     */
    fun login(phone: String, password: String, mode: Int) {
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        if (checkPhone(phone).and(checkPassword(password))) {
            mView.showLoading("登录中...")
            loginMode.login(phone, password, mode, object : HttpRequestCallBackKT<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    mView.hideLoading()
                    mView.loginSuccess(result)
                    if(result!=null) {
                        SPUtils.getInstance().put("HIS_TOKEN", result.AccessToken)
                    }
                }

                override fun onFail(e: APiExceptionKT) {
                    mView.hideLoading()
                    mView.loginFail(e)
                }
            })
        }
    }

    /**
     * 获取验证码
     */
    fun getCode(phone: String) {
        if (checkPhone(phone)) {
            mView.showLoading("发送中...")
            loginMode.getCode(phone, object : HttpRequestCallBackKT<String> {
                override fun onSuccess(result: String?) {
                    mView.hideLoading()
                    mView.showCheckToast("验证码已发送到您的手机")
                }

                override fun onFail(e: APiExceptionKT) {
                    mView.hideLoading()
                    mView.showCheckToast("验证码发送失败")
                }
            })
        }
    }

    /**
     * 输入变化验证，控制登录按钮状态
     */
    fun inputChange(phone: String, password: String) {
        val bool =
            phone.isEmpty() || password.isEmpty() || phone.length != 11 || !phone.startsWith("1") || password.length != 6
        mView.changeBtnStatus(!bool)
    }

    /**
     * 检测手机号有效性
     */
    private fun checkPhone(phone: String): Boolean {
        return if (phone.isEmpty()) {
            mView.showCheckToast("请输入手机号码")
            false
        } else if (!phone.startsWith("1") || phone.length != 11) {
            mView.showCheckToast("请输入正确的电话号码")
            false
        } else {
            true
        }
    }

    /**
     * 验证密码有效性
     */
    private fun checkPassword(password: String): Boolean {
        return if (password.isEmpty()) {
            mView.showCheckToast("请输入密码")
            false
        } else {
            true
        }
    }


}