package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.activity.main.order.OrderListPresenter
import com.hysyyl.bloodapp.model.Order

class OrderListViewModel(private val orderListPresenter: OrderListPresenter) : PageListBaseViewModel() {
    fun loadMoreData(): LiveData<PagedList<Order>> = LivePagedListBuilder(
        getDataSourceFactory(orderListPresenter), pageConfig
    ).build()

    private fun getDataSourceFactory(orderPresenter: OrderListPresenter): DataSource.Factory<Int, Order> {
        return object : DataSource.Factory<Int, Order>() {
            override fun create(): DataSource<Int, Order> {
                return getDataSource(orderPresenter)
            }
        }
    }

    private fun getDataSource(orderPresenter: OrderListPresenter): PageKeyedDataSource<Int, Order> {
        return object : PageKeyedDataSource<Int, Order>() {
            override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Order>) {
                orderPresenter.getOrderList(1, PAGE_SIZE, object : LoadListDataCallBack<Order> {
                    override fun onLoadListDataSuccess(list: List<Order>) {
                        if (list.size < PAGE_SIZE) {
                            callback.onResult(list, 1, null)
                        } else {
                            callback.onResult(list, 1, 2)
                        }
                    }
                })
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {
                val pageIndex = params.key
                orderPresenter.getOrderList(pageIndex, PAGE_SIZE, object : LoadListDataCallBack<Order> {
                    override fun onLoadListDataSuccess(list: List<Order>) {
                        callback.onResult(list, pageIndex + 1)
                    }
                })
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {

            }
        }
    }
}
