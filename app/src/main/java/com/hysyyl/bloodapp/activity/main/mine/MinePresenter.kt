package com.hysyyl.bloodapp.activity.main.mine

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Account
import com.hysyyl.bloodapp.model.Saler
import com.hysyyl.bloodapp.model.UserInfo
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

class MinePresenter : BasePresenter<MineView>() {
    val mineMomel: MineModel = MineModel()

    fun getUserInfo() {
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        mineMomel.getUserInfo(object : HttpRequestCallBackKT<UserInfo> {
            override fun onSuccess(result: UserInfo?) {
                mView.setUserInfo(result)
            }

            override fun onFail(e: APiExceptionKT) {
                mView.loadFail(e)
            }
        })
    }

    fun getUserBalance() {
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        mineMomel.getUserBalance(object : HttpRequestCallBackKT<Account> {
            override fun onSuccess(result: Account?) {
                mView.setUserBalance(result)
            }

            override fun onFail(e: APiExceptionKT) {
                mView.loadFail(e)
            }
        })
    }

    fun getUserSaler() {
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        mineMomel.getUserSaler(object : HttpRequestCallBackKT<Saler> {
            override fun onSuccess(result: Saler?) {
                mView.setSaler(result)
            }

            override fun onFail(e: APiExceptionKT) {
                mView.loadFail(e)
            }
        })
    }

    fun checkBindWeChat() {
        HttpUtils.getInstance().checkBindWechat(object : HttpRequestCallBackKT<String> {
            override fun onSuccess(result: String?) {
                mView.showCheckToast("检查绑定关系的接口调用成功")
            }

            override fun onFail(e: APiExceptionKT) {
                mView.showCheckToast("检查绑定关系的接口调用失败 失败  失败")
            }
        })
    }
}