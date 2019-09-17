package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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
class ReportListViewModel(reportListPresenter: ReportListPresenter) : ViewModel() {
    private val mReportPresenter = reportListPresenter
    fun loadReportData(keyWords: String): LiveData<PagedList<Report>> = LivePagedListBuilder(
        ReportDataSourceFactory(mReportPresenter, keyWords), PagedList.Config.Builder()
            .setPageSize(ReportListViewModel.PAGE_SIZE) //配置分页加载的数量
            .setEnablePlaceholders(ReportListViewModel.ENABLE_PLACEHOLDERS) //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(ReportListViewModel.PAGE_SIZE)  //初始化加载的数量
            .setPrefetchDistance(2)
            .build()
    ).build()


    companion object {
        private const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = false
    }

    class ReportDataSourceFactory(reportListPresenter: ReportListPresenter, keyWords: String) :
        DataSource.Factory<Int, Report>() {
        private val mReportListDataSource = ReportListDataSource(reportListPresenter, keyWords)
        override fun create(): DataSource<Int, Report> {
            return mReportListDataSource
        }
    }

    class ReportListDataSource(reportListPresenter: ReportListPresenter, keyWords: String) :
        PageKeyedDataSource<Int, Report>() {
        private val reportPresenter = reportListPresenter
        private val key = keyWords
        /**
         * 首次加载
         */
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Report>) {
            reportPresenter.loadReportListData(key, 1, PAGE_SIZE, object : LoadListDataCallBack<Report> {
                override fun onLoadListDataSuccess(list: List<Report>) {
                    callback.onResult(list, 1, 2)
                }
            })
        }

        /**
         * 加载后一页
         */
        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Report>) {
            val pageIndex = params.key
            reportPresenter.loadReportListData(key, pageIndex, PAGE_SIZE, object : LoadListDataCallBack<Report> {
                override fun onLoadListDataSuccess(list: List<Report>) {
                    callback.onResult(list, pageIndex + 1)
                }
            })
        }

        /**
         * 加载上一页
         */
        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Report>) {
        }
    }

}