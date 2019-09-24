package com.hysyyl.bloodapp.activity.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.video.VideoInfoActivity
import com.hysyyl.bloodapp.model.Video
import com.lpjeremy.libmodule.image.GlideImageUtil

class VideoListAdapter : PagedListAdapter<Video, VideoListAdapter.VideoViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setVideoInfo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Video>() {
            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem.ID == newItem.ID
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem == newItem
            }
        }
    }

    class VideoViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_video_layout,
                parent,
                false
            )
        ) {
        private val mContext: Context = parent.context
        private val videoName = itemView.findViewById<TextView>(R.id.videoName)
        private val videoImg = itemView.findViewById<ImageView>(R.id.videoImg)

        fun setVideoInfo(video: Video?) {
            videoName.text = video?.Name
            GlideImageUtil.getInstance().loadImage(mContext, videoImg, video?.CoverImgUrl)
//http://img.abhxc.com/6dm/dm360aavq.jpg

            videoImg.setOnClickListener {
                val cate: String = getCate(video?.CoverImgUrl)
//                val url = "http://video.aohxc.com:8603/" + cate + "/" + video?.No + "/index.m3u8"
                val url = ".aohxc.com:8603/" + cate + "/" + video?.No + "/index.m3u8"
                //%s 1-10
                LogUtils.e("videoUrl = " + String.format(url, ""))

                val intent = Intent(mContext, VideoInfoActivity::class.java)
                intent.putExtra("videoUrl", url)
                intent.putExtra("videoName", video?.Name)
                mContext.startActivity(intent)
            }
        }

        private fun getCate(imgAdd: String?): String {
            var value = ""
            val imgUrl = imgAdd?.replace("http://", "")
            val index = imgUrl?.indexOf("/")
            if (index != null) {
                if (index > -1) {
                    val newStr = imgUrl.substring(index + 1)
                    val i = newStr.indexOf("/")
                    value = newStr.substring(0, i)
                }
            }
            return value
        }
    }
}