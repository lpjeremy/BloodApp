package com.hysyyl.bloodapp.viewmodel.base

import com.blankj.utilcode.util.ToastUtils
import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter
import com.lpjeremy.uimodule.mvp.MvpBaseView

open class ListViewPresenter<T>(listViewModel: ListViewModel<T>) : BasePresenter<MvpBaseView>() {
    private val mListViewModel = listViewModel
    /**
     * 加载数据
     */
    fun loadData(keyWords: String?, pageIndex: Int, pageSize: Int, callBack: LoadListDataCallBack<T>) {
        if (!isViewAttached) {
            ToastUtils.showShort("未绑定view")
            return
        }
        if (pageIndex > 1) {
            mView.showLoading()
        } else {
            mView.showLoadStateView()
        }
        mListViewModel.getListData(keyWords, pageIndex, pageSize, object : HttpRequestCallBackKT<List<T>> {
            override fun onSuccess(result: List<T>?) {
                if (result != null) {
                    callBack.onLoadListDataSuccess(result)
                }
                if (pageIndex > 1) {
                    mView.hideLoading()
                } else {
                    if (result != null && result.isNotEmpty()) {
                        mView.hideLoadStateView()
                    } else {
                        mView.showLoadStateView("暂无数据")
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