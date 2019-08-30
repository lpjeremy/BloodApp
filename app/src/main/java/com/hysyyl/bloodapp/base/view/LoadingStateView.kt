package com.hysyyl.bloodapp.base.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_loading_state.view.*

class LoadingStateView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val mHandler: Handler = Handler()

    override fun onFinishInflate() {
        super.onFinishInflate()
        showLoading(null)
    }

    /**
     * -1 隐藏LoadingStateView
     * 0 显示loadingImage 动画
     * 1 显示loadingImage 动画 + 文字
     * 2 显示图片提示 + 文字
     *
     */
    private fun showLoadingStateView(state: Int, toast: String?, imgResId: Int?) {
        mHandler.post {
            loadingTxtToast.text = toast
            if (imgResId != null && imgResId > 0) {
                loadingImgToast.setImageResource(imgResId)
            }
            when (state) {
                -1 -> {
                    visibility = View.GONE
                    loadingImage.stopLoading()
                }
                0 -> {
                    visibility = View.VISIBLE
                    loadingTxtToast.visibility = View.GONE
                    loadingImgToast.visibility = View.GONE
                    loadingImage.visibility = View.VISIBLE
                    loadingImage.startLoading()

                }
                1 -> {
                    visibility = View.VISIBLE
                    loadingTxtToast.visibility = View.VISIBLE
                    loadingImgToast.visibility = View.GONE
                    loadingImage.visibility = View.VISIBLE
                    loadingImage.startLoading()
                }
                2 -> {
                    visibility = View.VISIBLE
                    loadingTxtToast.visibility = View.VISIBLE
                    loadingImgToast.visibility = View.VISIBLE
                    loadingImage.visibility = View.GONE
                    loadingImage.stopLoading()
                }
            }
        }
    }

    /**
     * 隐藏loading view
     */
    fun hideLoadingView() {
        showLoadingStateView(-1, "", 0)
    }

    /**
     * 显示loading动画
     * @param toast 传null只显示loading图片动画  传空字符  显示loading图片动画和‘努力加载中...’
     */
    fun showLoading(toast: String?) {
        if (toast == null) {
            showLoadingStateView(0, "", 0)
        } else {
            var msg = if (toast.isEmpty()) "努力加载中..." else toast
            showLoadingStateView(1, msg, 0)
        }

    }

    /**
     * 显示加载结果
     */
    fun showLoadResult(toast: String, imgResId: Int) {
        showLoadingStateView(2, toast, imgResId)
    }

}