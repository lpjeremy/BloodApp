package com.hysyyl.bloodapp.activity.login.register

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.request.RegisterParams
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class RegisterModel {
    fun register(userName:String,password:String,callBack: HttpRequestCallBackKT<String>){
        HttpUtils.getInstance().register(userName,password,callBack)
    }

    fun register(params:RegisterParams,callBack: HttpRequestCallBackKT<String>){
        HttpUtils.getInstance().register(params,callBack)
    }

}