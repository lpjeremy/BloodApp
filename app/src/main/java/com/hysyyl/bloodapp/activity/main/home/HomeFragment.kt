package com.hysyyl.bloodapp.activity.main.home

import android.content.Intent
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.goods.GoodsInfoActivity
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    override fun initView() {
        homeSwipeRefreshLayout.setOnRefreshListener {
            homeSwipeRefreshLayout.isRefreshing = false
        }
        imgGoodsInfo.setOnClickListener {
            startActivity(Intent(mContext, GoodsInfoActivity::class.java))
        }
    }

    override fun initData() {

    }
}