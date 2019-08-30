package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.model.Order

class OrderViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order_layout, parent, false)) {
    private val txtOrderNo = itemView.findViewById<TextView>(R.id.txtOrderNo)
    private val txtGoodsName = itemView.findViewById<TextView>(R.id.txtOrderGoodsName)
    private val txtOrderPName = itemView.findViewById<TextView>(R.id.txtOrderPatientName)
    private val txtOrderPInfo = itemView.findViewById<TextView>(R.id.txtOrderPatientInfo)
    private val txtOrderTime = itemView.findViewById<TextView>(R.id.txtOrderTime)

    fun setOrderInfo(order: Order?) {
        txtOrderNo.text = "订单号：" + order?.OrderNo
        txtGoodsName.text = order?.GoodsName
        txtOrderPInfo.text = order?.Gender + order?.AgeYear
        txtOrderPName.text = order?.Name
        txtOrderTime.text = order?.CreateTimeStr
    }
}