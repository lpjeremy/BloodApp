package com.lpjeremy.uimodule.impl

interface IActivity {
    /**
     * 获取布局layout资源id
     * @return 布局资源id
     */
    fun getLayoutResId(): Int

    /**
     * activity oncreate中调用此方法
     * 此方法中调用initView和initData方法
     */
    fun onCreateInit()

    /**
     * 初始化view
     */
    fun initView()

    /**
     * 初始化数据
     */
    fun initData()

}