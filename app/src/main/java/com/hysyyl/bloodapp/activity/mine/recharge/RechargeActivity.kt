package com.hysyyl.bloodapp.activity.mine.recharge

import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.RechargeDataAdapter
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import com.hysyyl.bloodapp.model.RechargeConfig
import kotlinx.android.synthetic.main.activity_recharge.*
import kotlinx.android.synthetic.main.layout_loading_state.*

/**
 * @desc:充值界面
 * @date:2019/9/17 11:15
 * @auther:lp
 * @version:1.1.6
 */
class RechargeActivity : BloodBaseActivity() {
    override fun addToolBar(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_recharge
    }

    override fun initView() {

    }

    override fun initData() {

        val rechargeList = ArrayList<RechargeConfig>()
        for (index in 0..5) {
            val rConfig = RechargeConfig()
            val num = (index + 1) * 100
            rConfig.Id = index
            rConfig.Price = num
            rConfig.FreePrice = num * 0.2
            rechargeList.add(rConfig)
        }
        rechargeRecyclerView.adapter = RechargeDataAdapter(rechargeList)
        loadStateView.hideLoadingView()
    }
}