package com.hysyyl.bloodapp.activity.mine.userinfo

import android.graphics.Color
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import kotlinx.android.synthetic.main.activity_userinfo.*

class UserInfoActivity : BloodBaseActivity() {

    private var isMan: Boolean = false

    override fun addToolBar(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_userinfo
    }

    override fun initView() {
        layoutUserInfoMan.setOnClickListener {
            setSex(true)
        }
        layoutUserInfoWoMen.setOnClickListener {
            setSex(false)
        }

    }

    override fun initData() {

    }

    private fun setSex(isMan: Boolean) {
        this.isMan = isMan
        if (isMan) {
            imgUserInfoCheckedMan.visibility = View.VISIBLE
            txtUserInfoMan.setTextColor(Color.parseColor("#ff36cfc9"))

            imgUserInfoCheckedWoMen.visibility = View.INVISIBLE
            txtUserInfoWoMen.setTextColor(Color.parseColor("#ff333333"))
        } else {
            imgUserInfoCheckedMan.visibility = View.INVISIBLE
            txtUserInfoMan.setTextColor(Color.parseColor("#ff333333"))

            imgUserInfoCheckedWoMen.visibility = View.VISIBLE
            txtUserInfoWoMen.setTextColor(Color.parseColor("#ff36cfc9"))
        }
    }
}