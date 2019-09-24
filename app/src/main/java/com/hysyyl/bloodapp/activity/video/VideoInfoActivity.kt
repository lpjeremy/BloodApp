package com.hysyyl.bloodapp.activity.video

import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.activity_videoinfo.*

class VideoInfoActivity : BloodBaseActivity() {
    private val url_start = "http://video"
    private var url_end = ""
    private var name = ""

    override fun addToolBar(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_videoinfo
    }

    override fun initView() {
        url_end = intent.getStringExtra("videoUrl")
        name = intent.getStringExtra("videoName")

        btnVideoInfo.setOnClickListener {
            val url = url_start + edtVideoInfoNumber.text.toString().trim() + url_end
            videoInfoPlayerView.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, name)
            videoInfoPlayerView.startVideo()
        }
    }

    override fun initData() {

    }
}