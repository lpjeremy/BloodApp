package com.hysyyl.bloodapp.activity.main.order

import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.tabs.TabLayout
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.OrderListAdapter
import com.hysyyl.bloodapp.viewmodel.OrderListViewModel
import com.hysyyl.bloodapp.views.SearchLayout
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_loading_state.*
/**
 * @desc:订单列表
 * @date:2019/9/19 11:35
 * @auther:lp
 * @version:1.1.6
 */
class OrderFragment : BaseFragment(R.layout.fragment_order), OrderListView {
    private val orderListPresenter = OrderListPresenter()

    private val orderViewModel: OrderListViewModel = OrderListViewModel(orderListPresenter)
    private val mOrderListAdapter: OrderListAdapter = OrderListAdapter()
    private var mSearchLayout: SearchLayout? = null

    override fun initView() {
        orderListPresenter.attachView(this)
        mSearchLayout = orderSearchView as SearchLayout
        mSearchLayout?.showCallService()
        mSearchLayout?.setSearchHint("患者姓名/订单号/手机号")
        mSearchLayout?.addOnSearchViewChangeSearchListener(object : SearchLayout.OnSearchListener {
            override fun onSearch(searchKey: String) {
                LogUtils.e(searchKey)
            }
        })

        orderListTabLayout.addTab(orderListTabLayout.newTab().setText("全部"))
        orderListTabLayout.addTab(orderListTabLayout.newTab().setText("未服务"))
        orderListTabLayout.addTab(orderListTabLayout.newTab().setText("服务中"))
        orderListTabLayout.addTab(orderListTabLayout.newTab().setText("已服务"))
        orderListTabLayout.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                showCheckToast("当前选择是" + p0?.position)
            }
        })

        orderListRecyclerView.adapter = mOrderListAdapter

        orderListSwipeRefreshLayout.setOnRefreshListener {
            mOrderListAdapter.submitList(null)
            orderViewModel?.loadMoreData()?.observe(this, Observer { mOrderListAdapter?.submitList(it) })
//            orderViewModel.loadMoreData().value?.dataSource?.invalidate()
            orderListSwipeRefreshLayout.isRefreshing = false
        }

    }

    override fun initData() {
        orderViewModel.loadMoreData().observe(this, Observer { mOrderListAdapter.submitList(it) })
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

    override fun onDestroy() {
        super.onDestroy()
        orderListPresenter.detachView()
    }
}