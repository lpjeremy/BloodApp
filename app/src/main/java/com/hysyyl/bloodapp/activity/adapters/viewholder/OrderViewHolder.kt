package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
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
    private val txtOrderPSex = itemView.findViewById<TextView>(R.id.txtOrderPatientSex)
    private val txtOrderPAge = itemView.findViewById<TextView>(R.id.txtOrderPatientAge)
    private val txtOrderPInfo = itemView.findViewById<TextView>(R.id.txtOrderPatientStatus)
    private val txtOrderTime = itemView.findViewById<TextView>(R.id.txtOrderTime)
    private val txtOrderPayStatus = itemView.findViewById<TextView>(R.id.txtOrderPayStatus)
    private val txtOrderMoney = itemView.findViewById<TextView>(R.id.txtOrderMoney)
    private val txtOrderStatus = itemView.findViewById<TextView>(R.id.txtOrderStatus)

    fun setOrderInfo(order: Order?) {
        txtOrderMoney.text = "￥68.00"
        txtOrderStatus.text = "已结清"
        txtOrderNo.text = "订单号：" + order?.OrderNo
        txtGoodsName.text = order?.GoodsName

        txtOrderPSex.visibility = if (TextUtils.isEmpty(order?.Gender)) View.GONE else View.VISIBLE
        txtOrderPSex.text = order?.Gender

        var pInfoStr = ""
        if (!TextUtils.isEmpty(order?.AgeYear)) {
            pInfoStr = order?.AgeYear + "岁"
        }
        if (!TextUtils.isEmpty(order?.AgeMonth)) {
            pInfoStr += order?.AgeMonth + "月"
        }
        txtOrderPAge.visibility = if (TextUtils.isEmpty(pInfoStr)) View.GONE else View.VISIBLE
        txtOrderPAge.text = pInfoStr
        txtOrderPInfo.text = "信息未完善"
        txtOrderPName.text = order?.Name
        txtOrderTime.text = order?.CreateTimeStr
        txtOrderPayStatus.text = "订单未支付"
    }
}