package com.hysyyl.bloodapp.activity.login

//import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.main.MainActivity
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import com.hysyyl.bloodapp.model.LoginResult
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @desc:登录
 * @date:2019/8/13 17:18
 * @auther:lp
 * @version:1.1.6
 */
class LoginActivity : BloodBaseActivity(), View.OnClickListener, LoginView {

    private val loginPresenter: LoginPresenter = LoginPresenter()
    /**
     * 登录方式 1 密码登录 2 验证码登录
     */
    private var loginMode: Int = 1

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        loginPresenter.attachView(this)
        txtLoginChange.setOnClickListener(this)
        btnGetCode.setOnClickListener(this)
        txtForgotPwd.setOnClickListener(this)
        txtChangePhone.setOnClickListener(this)
        imgWechat.setOnClickListener(this)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                loginPresenter.inputChange(edtPhone.text.toString(), edtPwd.text.toString())
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        }
        edtPhone.addTextChangedListener(textWatcher)
        edtPwd.addTextChangedListener(textWatcher)
    }

    override fun initData() {
        edtPhone.text = Editable.Factory.getInstance().newEditable("13800138000")
        edtPwd.text = Editable.Factory.getInstance().newEditable("123456")

    }

    override fun addToolBar(): Boolean {
        return false
    }

    override fun changeBtnStatus(clickable: Boolean) {
        btnLogin.isSelected = clickable
        if (clickable) {
            KeyboardUtils.hideSoftInput(this)
            btnLogin.setOnClickListener(this)
        } else {
            btnLogin.setOnClickListener(null)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            btnLogin -> {
                KeyboardUtils.hideSoftInput(this)
                loginPresenter.login(edtPhone.text.toString(), edtPwd.text.toString(), loginMode)
            }
            txtLoginChange -> {
                loginMode = if (loginMode == 1) 2 else 1
                val value = if (loginMode == 1) "验证码登录" else "密码登录"
                val toast = if (loginMode == 1) "请输入密码" else "请输入验证码"
                val visibility = if (loginMode == 1) View.GONE else View.VISIBLE
                txtLoginChange.text = Editable.Factory.getInstance().newEditable(value)
                edtPwd.hint = toast
                btnGetCode.visibility = visibility
            }
            btnGetCode -> {
                loginPresenter.getCode(edtPhone.text.toString())
            }
            txtForgotPwd -> {
                showToast("忘记密码")
            }
            txtChangePhone -> {
                showToast("更换手机号")
            }
            imgWechat -> {
                showToast("微信登录")
            }
        }
    }

    override fun loginSuccess(userInfo: LoginResult?) {
        showToast(userInfo?.LoginName + "登录成功")
        val intent = Intent(mContext, MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginFail(failApi: APiExceptionKT) {
        showToast("登录失败")
    }

}
