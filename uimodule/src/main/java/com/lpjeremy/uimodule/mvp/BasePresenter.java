package com.lpjeremy.uimodule.mvp;

/**
 * @desc:Presenter基类
 * @date:2019/3/6 8:56
 * @auther:lp
 * @version:1.1.6
 */
public class BasePresenter<V extends MvpBaseView> {
    protected V mView;
    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */

    public void detachView() {
        this.mView = null;
    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}
