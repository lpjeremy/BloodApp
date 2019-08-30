package com.lpjeremy.uimodule.recyclerview.adpter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpjeremy.uimodule.recyclerview.CustomRecyclerView;
import com.lpjeremy.uimodule.recyclerview.interfaces.OnRvItemClickListener;

import java.text.DecimalFormat;
import java.util.List;


/**
 * describe: 所有数据适配器的父类
 * package: com.tezisuo.specialasset.adapter
 * ClinicUser: luopan(luopan@fenjinshe.com)
 * Date: 2016-08-02 14:56
 * version: 1.0
 */
public abstract class RvBaseAdapter<T> extends RecyclerView.Adapter<RvViewHolder> {
    private List<T> dataList;

    protected Context mContext;
    /**
     * 支持加入head和foot view的recyclerView，需要刷新数据就必须赋值
     */
    public CustomRecyclerView recyclerView;

    protected OnRvItemClickListener onItemClickListener;

    public RvBaseAdapter(Context mContext, List<T> list, CustomRecyclerView recyclerView) {
        this.mContext = mContext;
        this.dataList = list;
        this.recyclerView = recyclerView;
    }

    public RvBaseAdapter(Context mContext, CustomRecyclerView recyclerView) {

    }

    /**
     * 数据刷新的方法
     */
    public void notifyData() {
        if (recyclerView != null)
            recyclerView.notifyDataSetChanged();
    }

    /**
     * item除数刷新
     *
     * @param position
     */
    public void notifyItemRemove(int position) {
        dataList.remove(position);
        if (recyclerView != null) {
            recyclerView.notifyDataSetChanged();
        }
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void addDataToList(List<T> dataList) {
        if (this.dataList != null && dataList != null && !dataList.isEmpty()) {
            this.dataList.addAll(dataList);
            notifyData();
        }
    }

    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void clearData() {
        if (dataList != null && !dataList.isEmpty()) {
            dataList.clear();
            notifyData();
        }
    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    /**
     * 判断adapter是否为空
     *
     * @return true 空  false 非空
     */
    public boolean isEmpty() {
        return dataList == null || dataList.isEmpty();
    }

    /**
     * 获取Item的View
     *
     * @param resource
     * @param root
     * @return
     */
    protected View getItemRootView(int resource, ViewGroup root) {
        if (mContext == null || resource == 0 || root == null) return null;
        return LayoutInflater.from(mContext).inflate(resource, root, false);
    }

    public T getItem(int position) {
        try {
            return dataList == null || dataList.isEmpty() ? null : dataList.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取资源string
     *
     * @param resId
     * @return
     */

    public String getResourceString(int resId) {
        if (mContext == null) return "";

        return mContext.getResources().getString(resId);
    }

    public int getResourceColor(int cId) {
        if (mContext == null) return 0;
        return ContextCompat.getColor(mContext, cId);
    }

    /**
     * 格式化double数据 保留两位小数
     *
     * @param num
     * @return
     */
    protected String formatDoubleData(double num) {
        return new DecimalFormat("0.00").format(num);
    }

    protected String formatDoubleData(String num) {
        try {
            double dNum = Double.valueOf(num);
            return new DecimalFormat("0.00").format(dNum);
        } catch (Exception e) {
            return num;
        }
    }
}
