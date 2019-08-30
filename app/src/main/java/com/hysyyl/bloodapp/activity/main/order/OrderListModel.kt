package com.hysyyl.bloodapp.activity.main.order

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Order
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class OrderListModel {

    fun getOrderList(pageIndex: Int, pageSize: Int, callBack: HttpRequestCallBackKT<List<Order>>) {
        HttpUtils.getInstance().getUserOrderList("", 0, pageIndex, pageSize, callBack)
    }
}