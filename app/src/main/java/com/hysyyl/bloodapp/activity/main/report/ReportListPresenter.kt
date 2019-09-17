package com.hysyyl.bloodapp.activity.main.report

import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.model.Report
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

class ReportListPresenter : BasePresenter<ReportListView>() {
    private val reportListModel = ReportListModel()

    fun loadReportListData(key:String,pageIndex:Int,pageSize:Int,callBack:LoadListDataCallBack<Report>){
        if(!isViewAttached){
            mView.noAttachedView()
            return
        }
        if(pageIndex>1){
            mView.showLoading()
        }else{
            mView.showLoadStateView()
        }
        reportListModel.loadReportListData(key,pageIndex,pageSize,object : HttpRequestCallBackKT<List<Report>>{
            override fun onSuccess(result: List<Report>?) {
                if (result != null) {
                    callBack.onLoadListDataSuccess(result)
                }
                if (pageIndex > 1) {
                    mView.hideLoading()
                } else {
                    if (result != null && result.isNotEmpty()) {
                        mView.hideLoadStateView()
                    } else {
                        mView.showLoadStateView("没有报告数据")
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