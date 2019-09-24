package com.hysyyl.bloodapp.activity.video

import com.hysyyl.bloodapp.data.http.HttpUtils
import com.hysyyl.bloodapp.model.Video
import com.hysyyl.bloodapp.model.request.VideoRequest
import com.hysyyl.bloodapp.model.result.VideoResult
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT

class VideoListModel {

    fun getVideoList(params: VideoRequest, callBack: HttpRequestCallBackKT<List<Video>>) {
        HttpUtils.getInstance().getVideoList(params, callBack)
    }

    fun getVideoData(params: VideoRequest, callBack: HttpRequestCallBackKT<VideoResult>) {
        HttpUtils.getInstance().getVideoData(params, callBack)
    }
}