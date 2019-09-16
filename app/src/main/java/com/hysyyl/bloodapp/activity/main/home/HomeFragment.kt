package com.hysyyl.bloodapp.activity.main.home

import android.content.Intent
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.HomeGoodsListAdapter
import com.hysyyl.bloodapp.activity.goods.GoodsInfoActivity
import com.hysyyl.bloodapp.model.Goods
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
//        homeRecyclerView.isNestedScrollingEnabled = false
    }

    override fun initData() {
        val goodsList = arrayListOf<Goods>()
        for (i in 0..5) {
            val goods = Goods()
            goods.Name = "推荐商品组合名称"
            goods.Content = "血常规，肾功5项，血常规8项"
            goods.Describe = "推荐商品组合一句营销语"
            goods.Price = 30.80
            goods.OldPrice = 100.00

            goodsList.add(goods)
        }

        homeRecyclerView.adapter = HomeGoodsListAdapter(goodsList)
    }
}