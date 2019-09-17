package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.model.Order

class OrderViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order_layout, parent, false)) {
    private val mContext: Context = parent.context
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
    private val btnOrderPay = itemView.findViewById<TextView>(R.id.btnOrderPay)

    fun setOrderInfo(order: Order?) {
        txtOrderMoney.text = String.format(mContext.getString(R.string.money), order?.DueAmount)
        when (order?.PayStatus) {
            1 -> txtOrderStatus.text = "已支付"
            2 -> txtOrderStatus.text = "追加待付"
            3 -> txtOrderStatus.text = "已退款"
            4 -> txtOrderStatus.text = "部分退款"
        }

        txtOrderNo.text = "订单号：" + order?.OrderNo
        txtGoodsName.text = order?.GoodsName

        txtOrderTime.text = order?.CreateTimeStr
        txtOrderPayStatus.text = order?.OrderStatusRemark

        if (TextUtils.isEmpty(order?.Name)) {
            txtOrderPInfo.text = "信息未完善"
            txtOrderPName.visibility = View.INVISIBLE
            txtOrderPInfo.visibility = View.VISIBLE
            txtOrderPSex.visibility = View.GONE
            txtOrderPAge.visibility = View.GONE
        } else {

            txtOrderPName.visibility = View.VISIBLE
            txtOrderPInfo.visibility = View.GONE
            txtOrderPSex.visibility = View.VISIBLE
            txtOrderPAge.visibility = View.VISIBLE

            txtOrderPName.text = order?.Name

            txtOrderPSex.visibility = if (TextUtils.isEmpty(order?.Gender)) View.GONE else View.VISIBLE
            txtOrderPSex.text = order?.Gender

            var pInfoStr = ""
            if (order?.AgeYear!! > 0) {
                pInfoStr = String.format("%d岁", order?.AgeYear)
            }
            if (order?.AgeMonth > 0) {
                pInfoStr += String.format("%d月", order?.AgeMonth)
            }
            txtOrderPAge.visibility = if (TextUtils.isEmpty(pInfoStr)) View.GONE else View.VISIBLE
            txtOrderPAge.text = pInfoStr
        }

        if (order?.OrderStatus == 1 && order?.PayStatus == 0) {
            btnOrderPay.visibility = View.VISIBLE
            btnOrderPay.setOnClickListener {
                ToastUtils.showShort("支付订单")
            }
        } else {
            btnOrderPay.visibility = View.GONE
        }
    }
}