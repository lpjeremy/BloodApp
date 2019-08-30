package com.lpjeremy.libmodule.http.callback

import com.lpjeremy.libmodule.http.exception.APiExceptionKT

interface HttpRequestCallBackKT<T> {
    /**
     * 请求成功
     */
    fun onSuccess(result: T?)

    /**
     * 请求失败
     */
    fun onFail(e: APiExceptionKT)
}