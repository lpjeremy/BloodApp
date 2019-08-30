package com.hysyyl.bloodapp.activity.main.mine

import com.hysyyl.bloodapp.model.Account
import com.hysyyl.bloodapp.model.Saler
import com.hysyyl.bloodapp.model.UserInfo
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.MvpBaseView

interface MineView : MvpBaseView {

    fun setUserInfo(userINfo: UserInfo?)

    fun setUserBalance(account: Account?)

    fun setSaler(saler: Saler?)

    fun loadFail(e: APiExceptionKT)
}