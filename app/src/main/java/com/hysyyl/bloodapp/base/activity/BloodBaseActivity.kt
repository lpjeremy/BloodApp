package com.hysyyl.bloodapp.base.activity

import android.os.Build
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.hysyyl.bloodapp.BloodApplication
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.BaseActivity
import kotlinx.android.synthetic.main.layout_custom_toolbar.*

/**
 * @desc:血检项目base类
 * @date:2019/8/22 9:58
 * @auther:lp
 * @version:1.1.6
 */
abstract class BloodBaseActivity : BaseActivity() {

    lateinit var mApplication: BloodApplication

    override fun onCreateInit() {
        mApplication = application as BloodApplication
        if (addToolBar()) {
            addToolbarToThisActivity()
        }
        mApplication.addActivity(this)

        initView()
        initData()
    }

    /**
     * 是否添加toolbar  true 添加 false 不添加
     */
    abstract fun addToolBar(): Boolean

    /**
     * 添加toolbar
     */
    private fun addToolbarToThisActivity() {
        if (this is AppCompatActivity) {
            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setActionBar(toolBar as Toolbar)
                actionBar?.setDisplayShowTitleEnabled(false)
            }
        }
        toolbar_txtTitle?.text = title
        toolbar_txtBack?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun showLoadStateView() {
    }

    override fun showLoadStateView(toast: String?) {
    }

    override fun hideLoadStateView() {
    }

    override fun loadFail(e: APiExceptionKT?) {
    }
}