package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hysyyl.bloodapp.activity.main.order.LoadOrderListCallBack
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
        var orderListDataSource = OrderListDataSource(orderListPresenter)

        override fun create(): DataSource<Int, Order> {
            return orderListDataSource
        }
    }

    class OrderListDataSource(orderListPresenter: OrderListPresenter) : PageKeyedDataSource<Int, Order>() {
        private val orderPresenter = orderListPresenter
        /**
         * 首次加载
         */
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Order>) {
            orderPresenter.getOrderList(1, 10, object : LoadOrderListCallBack {
                override fun loadSuccess(orderList: List<Order>) {
                    callback.onResult(orderList, 1, 2)
                }
            })
//            var listOrder = mutableListOf<Order>()
//            for (i in 1..20) {
//                var order = Order()
//                order.name = "第" + i + "个order"
//                order.id = i
//                listOrder.add(order)
//            }
//            callback.onResult(listOrder, 1, 2)
        }

        /**
         * 加载后一页
         */
        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {
            val pageIndex = params.key
            orderPresenter.getOrderList(pageIndex, 10, object : LoadOrderListCallBack {
                override fun loadSuccess(orderList: List<Order>) {
                    callback.onResult(orderList, pageIndex + 1)
                }
            })
//            LogUtils.e("当前pageIndex = " + params.key)
//            var listOrder = mutableListOf<Order>()
//            val pageIndex = params.key
//            val start = (pageIndex - 1) * 20 + 1
//            val end = (pageIndex) * 20
//            for (i in start..end) {
//                var order = Order()
//                order.name = "第" + i + "个order"
//                order.id = i
//                listOrder.add(order)
//            }
//            callback.onResult(listOrder, pageIndex + 1)
        }

        /**
         * 加载上一页
         */
        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {

        }
    }
}
