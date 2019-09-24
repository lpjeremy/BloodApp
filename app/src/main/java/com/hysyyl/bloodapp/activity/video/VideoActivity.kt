package com.hysyyl.bloodapp.activity.video

import androidx.lifecycle.Observer
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.adapters.VideoListAdapter
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import com.hysyyl.bloodapp.viewmodel.VideoListViewModel
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.layout_loading_state.*


class VideoActivity : BloodBaseActivity(), VideoListView {
    private val videoListPresenter: VideoListPresenter = VideoListPresenter()

    private val videoListViewModel: VideoListViewModel = VideoListViewModel(videoListPresenter)
    private var videoListAdapter: VideoListAdapter = VideoListAdapter()

    override fun addToolBar(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_video
    }

    override fun initView() {
        videoListPresenter.attachView(this)
        videoRecyclerView.adapter = videoListAdapter

        videoSwipeRefreshLayout.setOnRefreshListener {
            videoListAdapter?.submitList(null)
            videoListViewModel?.loadVideoData().observe(this, Observer {
                videoListAdapter?.submitList(it)
            })
            videoSwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun initData() {
        videoListViewModel.loadVideoData().observe(this, Observer {
            videoListAdapter.submitList(it)
        })
    }

    override fun showLoadStateView() {
        super.showLoadStateView()
        loadStateView.showLoading("")
    }

    override fun showLoadStateView(toast: String?) {
        super.showLoadStateView(toast)
        if (toast != null) {
            loadStateView.showLoadResult(toast, R.drawable.err_img)
        } else {
            loadStateView.showLoading("")
        }
    }

    override fun hideLoadStateView() {
        super.hideLoadStateView()
        loadStateView.hideLoadingView()
    }
}