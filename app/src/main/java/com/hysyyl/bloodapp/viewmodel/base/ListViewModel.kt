package com.hysyyl.bloodapp.viewmodel.base

import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

interface ListViewModel<T> {
    fun getListData(
        keyWords: String?,
        params: MutableList<String>?,
        pageIndex: Int,
        pageSize: Int,
        callBack: HttpRequestCallBackKT<List<T>>
    )
}