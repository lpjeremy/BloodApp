package com.hysyyl.bloodapp.activity.impls
/**
 * @desc:分页列表数据加载统一回调接口
 * @date:2019/9/17 17:07
 * @auther:lp
 * @version:1.1.6
 */
interface LoadListDataCallBack<T> {
    /**
     * 加载成功
     */
    fun onLoadListDataSuccess(list: List<T>)
}