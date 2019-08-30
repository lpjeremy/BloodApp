package com.hysyyl.bloodapp.activity.main.order

import com.hysyyl.bloodapp.model.Order

 interface LoadOrderListCallBack {
    fun loadSuccess(orderList: List<Order>)
}