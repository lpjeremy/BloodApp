package com.hysyyl.bloodapp.activity.main.report

import androidx.lifecycle.Observer
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.ReportListAdapter
import com.hysyyl.bloodapp.model.Report
import com.hysyyl.bloodapp.viewmodel.ReportListViewModel
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_report.*

class ReportFragment : BaseFragment(R.layout.fragment_report) {
    private val viewModel = ReportListViewModel()
    override fun initView() {
        val reportListAdapter = ReportListAdapter() // ReportListAdapter(viewModel.getReportList())
        reportRecyclerView.adapter = reportListAdapter

        viewModel.getReportDatas().observe(this, Observer {
            reportListAdapter.setReportList(it)
        })

        btnChangeData.setOnClickListener {
            val reportList = arrayListOf<Report>()
            var end: Int = if (reportListAdapter.itemCount < 10) 20 else 3
            for (i in 0..end) {
                val report = Report()
                report.Name = "报告 " + (i + 1)
                reportList.add(report)
            }
            viewModel.getReportDatas().postValue(reportList)
        }
    }

    override fun initData() {

    }
}