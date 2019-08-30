package com.lpjeremy.uimodule.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
/**
 * @desc:加载动画Image
 * @date:2019/8/18 16:16
 * @auther:lp
 * @version:1.1.6
 */
open class LoadingImage(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {
    /**
     * AnimationDrawable
     */
    private var animationDrawable: AnimationDrawable = AnimationDrawable()
    /**
     * 每帧播放时长
     */
    var time: Int = 30
    /**
     * 是否循环播放
     */
    var loop: Boolean = true

    init {
        try {
            for (index in 1..31) {
                val id = resources.getIdentifier("loading_" + index, "drawable", getContext().packageName)
                val drawble = resources.getDrawable(id, resources.newTheme())
                animationDrawable.addFrame(drawble, time)
                animationDrawable.isOneShot = !loop
            }
            setImageDrawable(animationDrawable)
        } catch (e: Exception) {
            Log.e("LoadingImage", "")
        }
    }

    /**
     * 开启loading
     */
    fun startLoading() {
        animationDrawable.start()
    }

    /**
     * 结束loading
     */
    fun stopLoading() {
        animationDrawable.stop()
    }
}