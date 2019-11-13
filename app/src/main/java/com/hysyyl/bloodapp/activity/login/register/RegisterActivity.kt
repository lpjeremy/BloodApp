package com.hysyyl.bloodapp.activity.login.register

import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BloodBaseActivity(),RegisterView {

    private val mRegisterPresenter:RegisterPresenter = RegisterPresenter();

    override fun addToolBar(): Boolean {
        return false
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        mRegisterPresenter.attachView(this)
        btnRegister.setOnClickListener {
            mRegisterPresenter.register(edtPhone.text.toString().trim(),edtPwd.text.toString().trim())
        }
    }

    override fun initData() {

    }

    override fun onRegisterResult(result: Boolean) {
        if(result){
            showToast("注册成功,请登录")
//            onBackPressed()
        }else{
            showToast("注册失败")
        }
    }
}

