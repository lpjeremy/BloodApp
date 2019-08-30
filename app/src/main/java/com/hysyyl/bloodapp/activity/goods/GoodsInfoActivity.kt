package com.hysyyl.bloodapp.activity.goods

import com.google.android.material.tabs.TabLayout
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import kotlinx.android.synthetic.main.activity_goodsinfo.*

class GoodsInfoActivity : BloodBaseActivity(), TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
    override fun addToolBar(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_goodsinfo
    }

    override fun initView() {
        tabLayoutGoodsInfo.addTab(tabLayoutGoodsInfo.newTab().setText("商品介绍"))
        tabLayoutGoodsInfo.addTab(tabLayoutGoodsInfo.newTab().setText("图文介绍"))
        tabLayoutGoodsInfo.addTab(tabLayoutGoodsInfo.newTab().setText("报告实例"))
        tabLayoutGoodsInfo.addOnTabSelectedListener(this)
    }

    override fun initData() {

    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        goodsInfoContent.text = p0?.text
        showCheckToast("当前选择是" + p0?.position)
    }
}
