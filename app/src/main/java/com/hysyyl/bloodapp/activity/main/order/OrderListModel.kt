package com.hysyyl.bloodapp.activity.main.order

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Order
import com.hysyyl.bloodapp.viewmodel.base.ListViewModel
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

open class OrderListModel : ListViewModel<Order> {
    override fun getListData(
        keyWords: String?,
        params: MutableList<String>?,
        pageIndex: Int,
        pageSize: Int,
        callBack: HttpRequestCallBackKT<List<Order>>
    ) {
        HttpUtils.getInstance().getUserOrderList(keyWords, params?.get(0), pageIndex, pageSize, callBack)
    }
}