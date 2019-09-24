package com.hysyyl.bloodapp.activity.set

import android.content.Intent
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.video.VideoActivity
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import kotlinx.android.synthetic.main.activity_set.*

/**
 * @desc:设置界面
 * @date:2019/8/22 14:40
 * @auther:lp
 * @version:1.1.6
 */
class SetActivity : BloodBaseActivity() {
    override fun addToolBar(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_set
    }

    override fun initView() {
        setVideo.setOnClickListener {
            startActivity(Intent(mContext, VideoActivity::class.java))
        }
    }

    override fun initData() {

    }
}