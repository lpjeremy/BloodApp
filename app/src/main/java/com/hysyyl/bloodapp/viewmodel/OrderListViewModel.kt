package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.activity.main.order.OrderListPresenter
import com.hysyyl.bloodapp.model.Order

class OrderListViewModel(orderListPresenter: OrderListPresenter) : ViewModel() {
    private val orderDataSourceFactory = OrderDataSourceFactory(orderListPresenter)
    fun loadMoreData(): LiveData<PagedList<Order>> = LivePagedListBuilder(
        orderDataSourceFactory, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE) //配置分页加载的数量
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS) //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)  //初始化加载的数量
            .setPrefetchDistance(2)
            .build()
    ).build()


    companion object {
        private const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = false
    }

    class OrderDataSourceFactory(orderListPresenter: OrderListPresenter) : DataSource.Factory<Int, Order>() {
        private val orderPresenter = orderListPresenter
        override fun create(): DataSource<Int, Order> {
            return OrderListDataSource(orderPresenter)
        }
    }

    class OrderListDataSource(orderListPresenter: OrderListPresenter) : PageKeyedDataSource<Int, Order>() {
        private val orderPresenter = orderListPresenter
        /**
         * 首次加载
         */
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Order>) {
            orderPresenter.getOrderList(1, PAGE_SIZE, object : LoadListDataCallBack<Order> {
                override fun onLoadListDataSuccess(orderList: List<Order>) {
                    callback.onResult(orderList, 1, 2)
                }
            })
        }

        /**
         * 加载后一页
         */
        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {
            val pageIndex = params.key
            orderPresenter.getOrderList(pageIndex, PAGE_SIZE, object : LoadListDataCallBack<Order> {
                override fun onLoadListDataSuccess(orderList: List<Order>) {
                    callback.onResult(orderList, pageIndex + 1)
                }
            })
        }

        /**
         * 加载上一页
         */
        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {

        }
    }
}
