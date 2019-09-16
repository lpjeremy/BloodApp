package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.model.Report

class ReportViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_report_layout, parent, false)) {

    private val txtReportPatientName = itemView.findViewById<TextView>(R.id.txtReportPatientName)
    private val txtReportPatientSex = itemView.findViewById<TextView>(R.id.txtReportPatientSex)
    private val txtReportPatientAge = itemView.findViewById<TextView>(R.id.txtReportPatientAge)
    private val txtReportStatus = itemView.findViewById<TextView>(R.id.txtReportStatus)
    private val txtReportContent = itemView.findViewById<TextView>(R.id.txtReportContent)
    private val btnReport = itemView.findViewById<TextView>(R.id.btnReport)
    private val txtReportOrderNo = itemView.findViewById<TextView>(R.id.txtReportOrderNo)
    private val txtReportTime = itemView.findViewById<TextView>(R.id.txtReportTime)

    fun setReportInfo(report: Report?) {
        txtReportPatientName.text = report?.Name

    }
}