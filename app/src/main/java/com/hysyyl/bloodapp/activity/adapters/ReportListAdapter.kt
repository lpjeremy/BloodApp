package com.hysyyl.bloodapp.activity.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.activity.adapters.viewholder.ReportViewHolder
import com.hysyyl.bloodapp.model.Report

//class ReportListAdapter(reportList: MutableList<Report>) : RecyclerView.Adapter<ReportViewHolder>() {
class ReportListAdapter : RecyclerView.Adapter<ReportViewHolder>() {

    private var dataList: MutableList<Report>? = null

    fun setReportList(list: MutableList<Report>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.setReportInfo(dataList?.get(position))
    }
}
//class ReportListAdapter : PagedListAdapter<Report, ReportViewHolder>(diffCallBack) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
//        return ReportViewHolder(parent)
//    }
//
//    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
//        holder.setReportInfo(getItem(position))
//    }
//
//    companion object {
//        val diffCallBack = object : DiffUtil.ItemCallback<Report>() {
//            override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
//                return oldItem.Id == newItem.Id
//            }
//
//            @SuppressLint("DiffUtilEquals")
//            override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//}