package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hysyyl.bloodapp.activity.impls.LoadListDataCallBack
import com.hysyyl.bloodapp.activity.video.VideoListPresenter
import com.hysyyl.bloodapp.model.Video

class VideoListViewModel(private val videoListPresenter: VideoListPresenter) : PageListBaseViewModel() {

    fun loadVideoData(): LiveData<PagedList<Video>> = LivePagedListBuilder(
        getDataSourceFactory(videoListPresenter), pageConfig
    ).build()

    private fun getDataSourceFactory(videoListPresenter: VideoListPresenter): DataSource.Factory<Int, Video> {
        return object : DataSource.Factory<Int, Video>() {
            override fun create(): DataSource<Int, Video> {
                return getDataSource(videoListPresenter)
            }
        }
    }

    private fun getDataSource(videoListPresenter: VideoListPresenter): PageKeyedDataSource<Int, Video> {
        return object : PageKeyedDataSource<Int, Video>() {
            override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Video>) {
                videoListPresenter.getVideoList(1, PAGE_SIZE, object : LoadListDataCallBack<Video> {
                    override fun onLoadListDataSuccess(list: List<Video>) {
                        if (list.size < PAGE_SIZE) {
                            callback.onResult(list, 1, null)
                        } else {
                            callback.onResult(list, 1, 2)
                        }
                    }
                })
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Video>) {
                val pageIndex = params.key
                videoListPresenter.getVideoList(pageIndex, PAGE_SIZE, object : LoadListDataCallBack<Video> {
                    override fun onLoadListDataSuccess(list: List<Video>) {
                        callback.onResult(list, pageIndex + 1)
                    }
                })
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Video>) {

            }
        }
    }
}
