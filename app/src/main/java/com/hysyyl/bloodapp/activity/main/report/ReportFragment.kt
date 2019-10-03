package com.hysyyl.bloodapp.activity.main.report

import androidx.lifecycle.Observer
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.ReportListAdapter
import com.hysyyl.bloodapp.viewmodel.base.ListViewBaseModel
import com.hysyyl.bloodapp.views.SearchLayout
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.layout_loading_state.*

class ReportFragment : BaseFragment(R.layout.fragment_report), ReportListView {
    private val mReportListPresenter = ReportListPresenter(ReportListModel())
    private val mListViewModel = ListViewBaseModel(mReportListPresenter)

    private var mReportListAdapter = ReportListAdapter()
    private var mSearchLayout: SearchLayout? = null


    override fun initView() {
        mReportListPresenter.attachView(this)

        mSearchLayout = reportSearchView as SearchLayout
        mSearchLayout?.setSearchHint("搜索报告")
        mSearchLayout?.addOnSearchViewChangeSearchListener(object : SearchLayout.OnSearchListener {
            override fun onSearch(searchKey: String) {
                searchData()
            }
        })


        reportRecyclerView.adapter = mReportListAdapter

        reportSwipeRefreshLayout.setOnRefreshListener {
            mReportListAdapter?.submitList(null)
            searchData()
            reportSwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun initData() {
        searchData()
    }

    private fun searchData() {
        mListViewModel.loadListData(mSearchLayout?.getSearchValue(), null).observe(this, Observer {
            mReportListAdapter.submitList(it)
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