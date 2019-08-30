package com.hysyyl.bloodapp.activity.main.order

import com.hysyyl.bloodapp.model.Order
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

class OrderListPresenter : BasePresenter<OrderListView>() {
    val orderListMomel = OrderListModel()

    fun getOrderList(pageIndex: Int, pageSize: Int, callBack: LoadOrderListCallBack) {
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        if (pageIndex > 1) {
            mView.showLoading()
        } else {
            mView.showLoadStateView()
        }
        orderListMomel.getOrderList(pageIndex, pageSize, object : HttpRequestCallBackKT<List<Order>> {
            override fun onSuccess(result: List<Order>?) {
                if (result != null) {
                    callBack.loadSuccess(result)
                }
                if (pageIndex > 1) {
                    mView.hideLoading()
                } else {
                    if (result != null && result.isNotEmpty()) {
                        mView.hideLoadStateView()
                    } else {
                        mView.showLoadStateView("没有订单数据")
                    }
                }
            }

            override fun onFail(e: APiExceptionKT) {
                if (pageIndex > 1) {
                    mView.loadFail(e)
                } else {
                    mView.showLoadStateView(e.msg)
                }
            }
        })
    }
}