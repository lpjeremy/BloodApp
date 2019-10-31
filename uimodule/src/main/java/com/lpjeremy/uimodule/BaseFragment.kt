package com.lpjeremy.uimodule

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.dialog.ProgressDialog
import com.lpjeremy.uimodule.impl.IActivity
import com.lpjeremy.uimodule.mvp.Err
import com.lpjeremy.uimodule.mvp.MvpBaseView

/**
 * @desc:Fragment的base类
 * @date:2019/8/18 15:53
 * @auther:lp
 * @version:1.1.6
 */
abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId), MvpBaseView, IActivity {
    /**
     * Log打印使用的TAG
     */
    protected val TAG = this.javaClass.simpleName

    protected lateinit var mContext: Context
    protected var mApplication: Application? = null

    private val mHandler: Handler = Handler()

    lateinit var mProgressDialog: ProgressDialog


    private var isFragmentVisible: Boolean = false
    private var isReuseView: Boolean = false
    private var isFirstVisible: Boolean = false

    private var rootView: View? = null

    override fun onCreateInit() {
        initView()
        initData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (rootView == null) return

        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible()
            isFirstVisible = false
        }

        if (isVisibleToUser) {
            onFragmentVisibleChange(true)
            isFragmentVisible = true
            return
        }
        if (isFragmentVisible) {
            isFragmentVisible = false
            onFragmentVisibleChange(false)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this.context!!
        mApplication = activity?.application!!
        initVariable()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (rootView == null) {
            rootView = view
            if (userVisibleHint) {
                if (isFirstVisible) {
                    onFragmentFirstVisible()
                    isFirstVisible = false
                }
                onFragmentVisibleChange(true)
                isFragmentVisible = true
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }

    /**
     * 初始化变量
     */
    private fun initVariable() {
        isFirstVisible = true
        isFragmentVisible = false
        rootView = null
        isReuseView = true
    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    private fun onFragmentFirstVisible() {
        init()
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected fun onFragmentVisibleChange(isVisible: Boolean) {
        Log.d(TAG, "fragment visible is $isVisible")
    }

    private fun init() {
        mProgressDialog = ProgressDialog(mContext)
        initView()
        initData()
    }

    override fun getLayoutResId(): Int {
        return 0
    }

    /**
     * 显示progressbar
     */
    private fun showProgressBar(toast: String?) {
        if (toast == null || TextUtils.isEmpty(toast)) {
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

    override fun showLoadStateView() {
    }

    override fun showLoadStateView(toast: String?) {
    }

    override fun hideLoadStateView() {
    }

    override fun loadFail(e: APiExceptionKT?) {
        hideLoading()
    }
}