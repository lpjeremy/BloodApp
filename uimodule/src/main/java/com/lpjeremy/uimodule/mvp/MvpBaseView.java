package com.lpjeremy.uimodule.mvp;

import com.lpjeremy.libmodule.http.exception.APiExceptionKT;

public interface MvpBaseView {
    /**
     * 显示loading
     *
     * @param toast 指定显示文本
     */
    void showLoading(CharSequence toast);

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 显示操作loading
     */
    void showOperationLoading();

    /**
     * 隐藏loading
     */
    void hideLoading();

    /**
     * 错误
     *
     * @param err
     */
    void onError(Err err);

    /**
     * 未绑定View到presenter中
     */
    void noAttachedView();

    /**
     * 显示数据检查提示
     *
     * @param toast
     */
    void showCheckToast(String toast);

    /**
     * 显示数据检查提示
     *
     * @param toastId
     */
    void showCheckToast(int toastId);

    /**
     * 显示初始化加载view
     */
    void showLoadStateView();
    /**
     * 显示初始化加载view 带提示文本
     */
    void showLoadStateView(String toast);

    /**
     * 隐藏初始化加载view
     */
    void hideLoadStateView();

    /**
     * 数据加载失败
     * @param e
     */
    void loadFail(APiExceptionKT e);
}
