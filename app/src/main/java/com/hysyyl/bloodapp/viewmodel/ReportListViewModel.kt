package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.activity.main.report.ReportListPresenter
import com.hysyyl.bloodapp.model.Report

/**
 * @desc:报告的viewmodel
 * @date:2019/8/27 9:33
 * @auther:lp
 * @version:1.1.6
 */
class ReportListViewModel(private val mReportPresenter: ReportListPresenter) : PageListBaseViewModel() {

    fun loadReportData(keyWords: String): LiveData<PagedList<Report>> =
        LivePagedListBuilder(getDataSourceFactory(mReportPresenter, keyWords), pageConfig).build()

    private fun getDataSource(reportListPresenter: ReportListPresenter, keyWords: String): DataSource<Int, Report> {
        return object : PageKeyedDataSource<Int, Report>() {
            override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Report>) {
                reportListPresenter.loadReportListData(keyWords, 1, PAGE_SIZE, object : LoadListDataCallBack<Report> {
                    override fun onLoadListDataSuccess(list: List<Report>) {
                        if (list.size < PAGE_SIZE) {
                            callback.onResult(list, 1, null)
                        } else {
                            callback.onResult(list, 1, 2)
                        }

                    }
                })
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Report>) {
                val pageIndex = params.key
                reportListPresenter.loadReportListData(
                    keyWords,
                    pageIndex,
                    PAGE_SIZE,
                    object : LoadListDataCallBack<Report> {
                        override fun onLoadListDataSuccess(list: List<Report>) {
                            callback.onResult(list, pageIndex + 1)
                        }
                    })
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Report>) {

            }
        }
    }

    private fun getDataSourceFactory(
        reportListPresenter: ReportListPresenter,
        keyWords: String
    ): DataSource.Factory<Int, Report> {
        return object : DataSource.Factory<Int, Report>() {
            override fun create(): DataSource<Int, Report> {
                return getDataSource(reportListPresenter, keyWords)
            }
        }
    }
}