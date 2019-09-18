package com.hysyyl.bloodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

open class PageListBaseViewModel : ViewModel() {

    val pageConfig = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE) //设置每页加载的数量
        .setEnablePlaceholders(ENABLE_PLACEHOLDERS) //当item为null是否使用PlaceHolder展示
        .setInitialLoadSizeHint(PAGE_SIZE)  //初始化数据时加载的数量
        .setPrefetchDistance(3)//距加载内容的边缘多远才能触发
        .build()

    /**
     * 分页常量数据
     */
    companion object {
        const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = false
    }
}