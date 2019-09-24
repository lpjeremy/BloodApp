package com.hysyyl.bloodapp.data.http.api;


import com.hysyyl.bloodapp.model.Video;
import com.hysyyl.bloodapp.model.request.VideoRequest;
import com.hysyyl.bloodapp.model.result.VideoResult;
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

import java.util.List;

public interface VideoApi {
    void getVideoList(VideoRequest videoRequest, HttpRequestCallBackKT<List<Video>> callBack);

    void getVideoData(VideoRequest videoRequest, HttpRequestCallBackKT<VideoResult> callBack);
}
