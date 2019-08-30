package com.hysyyl.bloodapp.activity.main.order

import androidx.lifecycle.Observer
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.OrderListAdapter
import com.hysyyl.bloodapp.viewmodel.OrderListViewModel
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_loading_state.*

class OrderFragment : BaseFragment(R.layout.fragment_order), OrderListView {
    private val orderListPresenter = OrderListPresenter()

    private val orderViewModel: OrderListViewModel = OrderListViewModel(orderListPresenter)

    override fun initView() {
        orderListPresenter.attachView(this)
        val adapter = OrderListAdapter()
        orderListRecyclerView.adapter = adapter

        orderViewModel.loadMoreData().observe(this, Observer { adapter.submitList(it) })


        orderListSwipeRefreshLayout.setOnRefreshListener {
            adapter.submitList(null)
            orderViewModel?.loadMoreData()?.observe(this, Observer { adapter.submitList(it) })
            orderListSwipeRefreshLayout.isRefreshing = false
        }

    }

    override fun initData() {
    }

    override fun showLoadStateView() {
//        post(Runnable {
        loadStateView.showLoading("")
//        })
    }

    override fun showLoadStateView(toast: String) {
//        post(Runnable {
        loadStateView.showLoadResult(toast, R.drawable.err_img)
//        })
    }

    override fun hideLoadStateView() {
//        post(Runnable {
        loadStateView.hideLoadingView()
//        })
    }

    override fun loadFail(e: APiExceptionKT) {

    }

    override fun onDestroy() {
        super.onDestroy()
        orderListPresenter.detachView()
    }
}