package com.hysyyl.bloodapp.activity.main.order

import com.hysyyl.bloodapp.model.Order
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.MvpBaseView

interface OrderListView : MvpBaseView {

    fun showLoadStateView()

    fun showLoadStateView(toast:String)

    fun hideLoadStateView()

    fun loadFail(e: APiExceptionKT)
}