package com.lpjeremy.uimodule

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.lpjeremy.uimodule.dialog.ProgressDialog
import com.lpjeremy.uimodule.impl.IActivity
import com.lpjeremy.uimodule.mvp.Err
import com.lpjeremy.uimodule.mvp.MvpBaseView

/**
 * @desc:基础的base类
 * @date:2019/8/18 15:53
 * @auther:lp
 * @version:1.1.6
 */
abstract class BaseActivity : AppCompatActivity(), MvpBaseView, IActivity {
    /**
     * Log打印使用的TAG
     */
    protected val TAG = this.javaClass.simpleName

    lateinit var mContext: Context

    private val mHandler: Handler = Handler()

    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        mContext = this
        mProgressDialog = ProgressDialog(mContext)
        onCreateInit()
    }
    /**
     * 显示progressbar
     */
    private fun showProgressBar(toast: String?) {
        if (isFinishing || toast == null || TextUtils.isEmpty(toast)) {
            return
        }
        if (mProgressDialog.isShowing) {
            mProgressDialog.setmTxtProgressValue(toast)
        } else {
            post(Runnable {
                mProgressDialog.showProgressBar(toast)
            })
        }
    }

    fun post(runnable: Runnable) {
        mHandler.post(runnable)
    }

    fun showToast(value: String?) {
        if (!TextUtils.isEmpty(value)) {
            ToastUtils.showShort(value)
        }
    }

    fun showToast(resId: Int) {
        ToastUtils.showShort(resId)
    }

    override fun showLoading(toast: CharSequence?) {
        showProgressBar(toast?.toString())
    }

    override fun showLoading() {
        showProgressBar("努力加载中...")
    }

    override fun showOperationLoading() {
        showProgressBar("操作中...")
    }

    override fun hideLoading() {
        post(Runnable {
            mProgressDialog.dismiss()
        })
    }

    override fun onError(err: Err?) {
        showToast(err?.errMsg)
    }

    override fun noAttachedView() {
        showToast("未绑定当前view")
    }

    override fun showCheckToast(toast: String?) {
        showToast(toast)
    }

    override fun showCheckToast(toastId: Int) {
        showToast(toastId)
    }
}