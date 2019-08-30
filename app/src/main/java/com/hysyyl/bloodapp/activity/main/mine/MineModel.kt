package com.hysyyl.bloodapp.activity.main.mine

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Account
import com.hysyyl.bloodapp.model.Saler
import com.hysyyl.bloodapp.model.UserInfo
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class MineModel {

    fun getUserInfo(callBack: HttpRequestCallBackKT<UserInfo>) {
        HttpUtils.getInstance().getUserInfo(callBack)
    }

    fun  getUserBalance(callBack: HttpRequestCallBackKT<Account>) {
        HttpUtils.getInstance().getUserBalance(callBack)
    }
    fun getUserSaler(callBack: HttpRequestCallBackKT<Saler>) {
        HttpUtils.getInstance().getUserSaler(callBack)
    }
}