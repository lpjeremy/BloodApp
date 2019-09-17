package com.hysyyl.bloodapp.activity.main.report

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Report
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class ReportListModel {
    fun loadReportListData(keyWords:String,pageIndex:Int,pageSize:Int,callback: HttpRequestCallBackKT<List<Report>>){
        HttpUtils.getInstance().loadReportData(keyWords,pageIndex,pageSize,callback)
    }
}