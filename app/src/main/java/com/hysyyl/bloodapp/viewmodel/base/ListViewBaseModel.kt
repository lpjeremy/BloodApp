package com.hysyyl.bloodapp.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack

/**
 * @desc:列表式的下拉自动加载数据的viewModel
 * @date:2019/10/3 15:09
 * @auther:lp
 * @version:1.1.6
 */
class ListViewBaseModel<T>(private val listViewPresenter: ListViewPresenter<T>) : PageListBaseViewModel() {

    fun loadListData(keyWords: String?, params: MutableList<String>?): LiveData<PagedList<T>> = LivePagedListBuilder(
        getDataSourceFactory(keyWords, params, listViewPresenter), pageConfig
    ).build()

    private fun getDataSourceFactory(
        keyWords: String?,
        params: MutableList<String>?,
        listViewPresenter: ListViewPresenter<T>
    ): DataSource.Factory<Int, T> {
        return object : DataSource.Factory<Int, T>() {
            override fun create(): DataSource<Int, T> {
                return getDataSource(keyWords, params, listViewPresenter)
            }
        }
    }

    private fun getDataSource(
        keyWords: String?,
        paramsList: MutableList<String>?,
        listViewPresenter: ListViewPresenter<T>
    ): PageKeyedDataSource<Int, T> {
        return object : PageKeyedDataSource<Int, T>() {
            override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
                listViewPresenter.loadData(keyWords, 1, PAGE_SIZE, paramsList, object : LoadListDataCallBack<T> {
                    override fun onLoadListDataSuccess(list: List<T>) {
                        if (list.size < PAGE_SIZE) {
                            callback.onResult(list, 1, null)
                        } else {
                            callback.onResult(list, 1, 2)
                        }
                    }
                })
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
                val pageIndex = params.key
                listViewPresenter.loadData(
                    keyWords,
                    pageIndex,
                    PAGE_SIZE,
                    paramsList,
                    object : LoadListDataCallBack<T> {
                        override fun onLoadListDataSuccess(list: List<T>) {
                            callback.onResult(list, pageIndex + 1)
                        }
                    })
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

            }
        }
    }
}
