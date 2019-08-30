package com.hysyyl.bloodapp.activity.mine.account

import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
/**
 * @desc:账户流水
 * @date:2019/8/29 10:31
 * @auther:lp
 * @version:1.1.6
 */
class AccountFlowActivity : BloodBaseActivity(){
    override fun addToolBar(): Boolean {
       return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_account_flow
    }

    override fun initView() {

    }

    override fun initData() {
    }
}