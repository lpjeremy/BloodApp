package com.hysyyl.bloodapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.layout_search_view.view.*
import java.util.concurrent.TimeUnit

/**
 * @desc:项目中用到的搜索框view
 * @date:2019/9/19 9:27
 * @auther:lp
 * @version:1.1.6
 */
class SearchLayout : LinearLayout {
    private var onSearchListener: OnSearchListener? = null
    /**
     * 搜索时是否隐藏软键盘 默认不关闭
     */
    private var hideKeyboard: Boolean = false


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        searchViewImgService.visibility = View.GONE
        searchViewImgService.setOnClickListener {
            ToastUtils.showShort("跳转到客服界面")
        }

//        searchViewEdtSearch.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                LogUtils.e("afterTextChanged()")
////                onSearchViewChangeSearchListener?.onSearch(p0.toString())
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                LogUtils.e("beforeTextChanged()")
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                LogUtils.e("onTextChanged()")
//            }
//        })

        searchViewEdtSearch.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                when (p1) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        onSearchListener?.onSearch(searchViewEdtSearch.text.toString().trim())
                        //点击软键盘enter搜索键，必须关闭软键盘
                        KeyboardUtils.hideSoftInput(searchViewEdtSearch)
                    }
                }
                return false
            }
        })

        RxTextView.textChangeEvents(searchViewEdtSearch).debounce(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TextViewTextChangeEvent> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: TextViewTextChangeEvent) {
                    onSearchListener?.onSearch(getSearchValue())
                }

                override fun onError(e: Throwable) {

                }
            })

    }

    /**
     * 获取输入框文本
     *
     * @return
     */
    open fun getSearchValue(): String {
        return searchViewEdtSearch.text.toString().trim()
    }

    /**
     * 设置搜索提示文字
     */
    fun setSearchHint(hint: String) {
        searchViewEdtSearch.hint = hint
    }

    /**
     * 显示联系客服图片
     */
    fun showCallService() {
        searchViewImgService.visibility = View.VISIBLE
    }

    /**
     * 添加搜索监听器
     */
    fun addOnSearchViewChangeSearchListener(searchViewChangeSearchListener: OnSearchListener) {
        onSearchListener = searchViewChangeSearchListener
    }

    /**
     * 搜索框内容变化搜索监听器
     */
    interface OnSearchListener {
        /**
         * 搜索
         */
        fun onSearch(searchKey: String)
    }
}