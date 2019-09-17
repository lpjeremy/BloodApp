package com.hysyyl.bloodapp.activity.main.report

import androidx.lifecycle.Observer
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.ReportListAdapter
import com.hysyyl.bloodapp.viewmodel.ReportListViewModel
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.layout_loading_state.*

class ReportFragment : BaseFragment(R.layout.fragment_report), ReportListView {
    private val mReportListPresenter = ReportListPresenter()
    private val mReportViewModel = ReportListViewModel(mReportListPresenter)
    private val reportListAdapter = ReportListAdapter()

    override fun initView() {
        mReportListPresenter.attachView(this)
        reportRecyclerView.adapter = reportListAdapter

        reportSwipeRefreshLayout.setOnRefreshListener {
            reportListAdapter.submitList(null)
            mReportViewModel?.loadReportData("")?.observe(this, Observer {
                reportListAdapter?.submitList(it)
            })
            reportSwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun initData() {
        mReportViewModel.loadReportData("").observe(this, Observer {
            reportListAdapter.submitList(it)
        })
    }


    override fun showLoadStateView() {
        super.showLoadStateView()
        loadStateView.showLoading("")
    }

    override fun showLoadStateView(toast: String?) {
        super.showLoadStateView(toast)
        if (toast != null) {
            loadStateView.showLoadResult(toast, R.drawable.err_img)
        } else {
            loadStateView.showLoading("")
        }
    }

    override fun hideLoadStateView() {
        super.hideLoadStateView()
        loadStateView.hideLoadingView()
    }

}