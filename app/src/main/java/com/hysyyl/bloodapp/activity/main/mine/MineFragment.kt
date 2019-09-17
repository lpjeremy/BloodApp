package com.hysyyl.bloodapp.activity.main.mine

import android.content.Intent
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.mine.account.AccountFlowActivity
import com.hysyyl.bloodapp.activity.mine.recharge.RechargeActivity
import com.hysyyl.bloodapp.activity.mine.userinfo.UserInfoActivity
import com.hysyyl.bloodapp.activity.set.SetActivity
import com.hysyyl.bloodapp.model.Account
import com.hysyyl.bloodapp.model.Saler
import com.hysyyl.bloodapp.model.UserInfo
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.libmodule.image.GlideImageUtil
import com.lpjeremy.uimodule.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment(R.layout.fragment_mine), MineView {
    private val minePresenter: MinePresenter = MinePresenter()

    override fun initView() {
        minePresenter.attachView(this)
        txtCouponNum.setOnClickListener {
            this.showLoading("加载中...")
        }
        imgSet.setOnClickListener {
            startActivity(Intent(mContext, SetActivity::class.java))
        }
        mineUserInfoLayout.setOnClickListener {
            startActivity(Intent(mContext, UserInfoActivity::class.java))
        }
        txtMineBalance.setOnClickListener {
            startActivity(Intent(mContext, AccountFlowActivity::class.java))
        }
        txtMineRecharge.setOnClickListener {
            startActivity(Intent(mContext, RechargeActivity::class.java))
        }
    }

    override fun initData() {
        showLoading("加载中...")
        minePresenter.getUserInfo()
    }

    override fun setUserInfo(userInfo: UserInfo?) {
        mineTxtName?.text = userInfo?.CustomerName
        mineTxtAdd?.text = userInfo?.ClinicAddress?.getAddress(true)
        GlideImageUtil.getInstance().loadImage(mContext, mineImgHead, userInfo?.HeadImage)

        minePresenter.getUserBalance()
    }

    override fun setUserBalance(account: Account?) {
        txtMineBalance?.text = account?.Balance.toString()
        txtCouponNum?.text = account?.CouponCount.toString()

        minePresenter.getUserSaler()
    }

    override fun setSaler(saler: Saler?) {
        txtMineSalerName?.text = saler?.SalerName
        txtMineSalerPhone?.text = saler?.SalerPhone
        hideLoading()
    }
}