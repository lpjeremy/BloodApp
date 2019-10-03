package com.hysyyl.bloodapp.activity.main.report

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Report
import com.hysyyl.bloodapp.viewmodel.base.ListViewModel
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class ReportListModel : ListViewModel<Report> {
    override fun getListData(
        keyWords: String?,
        params: MutableList<String>?,
        pageIndex: Int,
        pageSize: Int,
        callBack: HttpRequestCallBackKT<List<Report>>
    ) {
        HttpUtils.getInstance().loadReportData(keyWords, pageIndex, pageSize, callBack)
    }
}