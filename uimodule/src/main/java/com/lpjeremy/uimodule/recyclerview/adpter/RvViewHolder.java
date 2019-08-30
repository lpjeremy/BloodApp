package com.lpjeremy.uimodule.recyclerview.adpter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.lpjeremy.uimodule.recyclerview.interfaces.OnRvItemClickListener;
import com.lpjeremy.uimodule.recyclerview.interfaces.OnRvItemLongClickListener;

/**
 * @desc:RecyclerView.ViewHolder的继承类
 * @date:2017/9/13 11:32
 * @auther:lp
 * @version:1.0
 */

public class RvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private OnRvItemClickListener onItemClickListener;
    private OnRvItemLongClickListener onItemLongClickListener;

    public RvViewHolder(View itemView) {
        super(itemView);
        setClickListener(itemView);
    }

    public RvViewHolder(View itemView, OnRvItemClickListener onItemClickListener, OnRvItemLongClickListener onItemLongClickListener) {
        super(itemView);
        setClickListener(itemView);
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public RvViewHolder(View itemView, OnRvItemLongClickListener onItemLongClickListener) {
        super(itemView);
        setClickListener(itemView);
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public RvViewHolder(View itemView, OnRvItemClickListener onItemClickListener) {
        super(itemView);
        setClickListener(itemView);
        this.onItemClickListener = onItemClickListener;
    }

    private void setClickListener(View itemView) {
        if (itemView != null) {
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(v, getAdapterPosition());
        }
        return true;
    }

    /**
     * 获取组件
     *
     * @param resId
     * @param parent
     * @param <T>
     * @return
     */
    protected <T extends View> T getViewById(int resId, View parent) {
        return (T) parent.findViewById(resId);
    }
}
