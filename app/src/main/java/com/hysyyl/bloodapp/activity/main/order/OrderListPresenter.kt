package com.hysyyl.bloodapp.activity.main.order

import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.model.Order
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

class OrderListPresenter : BasePresenter<OrderListView>() {
    private val orderListModel = OrderListModel()

    fun getOrderList(pageIndex: Int, pageSize: Int, callBack: LoadListDataCallBack<Order>) {
        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        if (pageIndex > 1) {
            mView.showLoading()
        } else {
            mView.showLoadStateView()
        }
        orderListModel.getOrderList(pageIndex, pageSize, object : HttpRequestCallBackKT<List<Order>> {
            override fun onSuccess(result: List<Order>?) {
                if (result != null) {
                    callBack.onLoadListDataSuccess(result)
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