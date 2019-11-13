package com.hysyyl.bloodapp.activity.login.register

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class RegisterModel {
    fun register(userName:String,password:String,callBack: HttpRequestCallBackKT<String>){
        HttpUtils.getInstance().register(userName,password,callBack)
    }

}