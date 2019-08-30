package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hysyyl.bloodapp.model.Report

/**
 * @desc:报告的viewmodel
 * @date:2019/8/27 9:33
 * @auther:lp
 * @version:1.1.6
 */
class ReportListViewModel : ViewModel() {
    private var reportData: MutableLiveData<ArrayList<Report>> = MutableLiveData()

    fun getReportList(): MutableList<Report> {
        val reportList = mutableListOf<Report>()
        for (i in 0..20) {
            val report = Report()
            report.Name = "报告 " + (i + 1)
            reportList.add(report)
        }
        return reportList
    }

    fun getReportDatas(): MutableLiveData<ArrayList<Report>> {
        val reportList = arrayListOf<Report>()
        for (i in 0..3) {
            val report = Report()
            report.Name = "报告 " + (i + 1)
            reportList.add(report)
        }
        reportData.postValue(reportList)
        return reportData
    }

}