package com.hysyyl.bloodapp.data.http.api;

import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

public interface AccountApi {
    void register(String userName, String password, HttpRequestCallBackKT<String> callBackKT);
}
