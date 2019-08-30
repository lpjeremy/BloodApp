package com.lpjeremy.libmodule.http

import android.annotation.SuppressLint
import com.blankj.utilcode.util.NetworkUtils
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.libmodule.http.model.BaseResult
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class HttpPresenterKT {

    @SuppressLint("MissingPermission")//权限申请
    fun <T> execute(observable: Observable<BaseResult<T>>, callBack: HttpRequestCallBackKT<T>) {
        if (!NetworkUtils.isConnected()) {
            val failApi = APiExceptionKT(ApiCode.Request.NETWORK_ERROR, "网络连接异常")
            callBack.onFail(failApi)
            return
        }
//        observable.compose(ObservableTransformer() { upstream ->
//            upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//        })
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseResult<T>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseResult<T>) {
                    if (t.isSuccess) {
                        callBack.onSuccess(t.data)
                    } else {
                        var failApi = APiExceptionKT(t.errorCode, t.msg)
                        callBack.onFail(failApi)
                    }
                }

                override fun onError(e: Throwable) {
                    val apie = APiExceptionKT(ApiCode.Request.UNKNOWN, e.message as String)
                    callBack.onFail(apie)
                }
            })
    }
}
