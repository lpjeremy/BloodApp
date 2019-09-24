package com.hysyyl.bloodapp.activity.video

import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.model.Video
import com.hysyyl.bloodapp.model.request.Ordertext
import com.hysyyl.bloodapp.model.request.VideoRequest
import com.hysyyl.bloodapp.model.result.VideoResult
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT
import com.lpjeremy.libmodule.http.exception.APiExceptionKT
import com.lpjeremy.uimodule.mvp.BasePresenter

class VideoListPresenter : BasePresenter<VideoListView>() {
    private val videoListModel = VideoListModel()
    private val ordertext: Ordertext = Ordertext("AddTime", "desc")

    fun getVideoList(pageIndex: Int, pageSize: Int, callBack: LoadListDataCallBack<Video>) {


        if (!isViewAttached) {
            mView.noAttachedView()
            return
        }
        if (pageIndex > 1) {
            mView.showLoading()
        } else {
            mView.showLoadStateView()
        }
        val list = arrayListOf<Ordertext>()
        list.add(ordertext)
        val start = (pageIndex - 1) * pageSize
        val params = VideoRequest(1, pageSize, list, "", start)

        videoListModel.getVideoData(params, object : HttpRequestCallBackKT<VideoResult> {
            override fun onSuccess(result: VideoResult?) {
                if (pageIndex > 1) {
                    mView.hideLoading()
                } else {
                    if (result?.data != null && result.data.isNotEmpty()) {
                        if (result != null) {
                            callBack.onLoadListDataSuccess(result.data)
                        }
                        mView.hideLoadStateView()
                    } else {
                        mView.showLoadStateView("没有视频数据")
                    }
                }
            }

            override fun onFail(e: APiExceptionKT) {
                if (pageIndex > 1) {
                    mView.loadFail(e)
                } else {
                    mView.showLoadStateView(e.msg)
                }
            }
        })
    }
}