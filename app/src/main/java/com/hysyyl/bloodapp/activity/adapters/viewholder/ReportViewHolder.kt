package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.model.Report

class ReportViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_report_layout, parent, false)) {

    private val txtReportName = itemView.findViewById<TextView>(R.id.txtReportName)

    fun setReportInfo(report: Report?) {
        txtReportName.text = report?.Name
    }
}