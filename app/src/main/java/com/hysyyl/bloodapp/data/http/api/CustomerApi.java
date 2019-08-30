package com.hysyyl.bloodapp.data.http.api;


import com.hysyyl.bloodapp.model.Account;
import com.hysyyl.bloodapp.model.Saler;
import com.hysyyl.bloodapp.model.UserInfo;
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

public interface CustomerApi {
    /**
     * 获取用户信息
     * @param callBack
     */
    void getUserInfo(HttpRequestCallBackKT<UserInfo> callBack);

    /**
     * 获取账户信息
     * @param callBack
     */
    void getUserBalance(HttpRequestCallBackKT<Account> callBack);

    /**
     * 获取用户的业务员信息
     * @param callBack
     */
    void getUserSaler(HttpRequestCallBackKT<Saler> callBack);


}
