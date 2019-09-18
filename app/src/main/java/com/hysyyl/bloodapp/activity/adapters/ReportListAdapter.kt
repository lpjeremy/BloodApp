package com.hysyyl.bloodapp.activity.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.hysyyl.bloodapp.activity.adapters.viewholder.ReportViewHolder
import com.hysyyl.bloodapp.model.Report

/**
 * @desc:报告列表数据适配器
 * @date:2019/9/17 16:36
 * @auther:lp
 * @version:1.1.6
 */
class ReportListAdapter : PagedListAdapter<Report, ReportViewHolder>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.setReportInfo(getItem(position))
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Report>() {
            override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
                return oldItem.OrderId == newItem.OrderId
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
                return oldItem == newItem
            }
        }
    }
}

