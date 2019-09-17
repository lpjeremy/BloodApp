package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.model.RechargeConfig

class RechargeItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recharge_layout, parent, false)) {
    private val txtPrice = itemView.findViewById<TextView>(R.id.txtRechargeItemPrice)
    private val txtFreeMoney = itemView.findViewById<TextView>(R.id.txtRechargeItemFreeMoney)
    val itemLayout = itemView.findViewById<LinearLayout>(R.id.txtRechargeItemLayout)
    private val itemParentLayout = itemView.findViewById<LinearLayout>(R.id.txtRechargeItemParent)

    fun setRechargeItemData(rechargeConfig: RechargeConfig, position: Int) {
        txtPrice.text = String.format("%d元", rechargeConfig.Price)
        txtFreeMoney.text = String.format("送%s", rechargeConfig.FreePrice.toString())
        txtPrice.setTextColor(if (rechargeConfig.checked) Color.WHITE else Color.parseColor("#ff999999"))
        txtFreeMoney.setTextColor(if (rechargeConfig.checked) Color.WHITE else Color.parseColor("#ff999999"))
        itemParentLayout.setBackgroundResource(if (rechargeConfig.checked) R.drawable.bg_36cfc9_radius4 else R.drawable.border_36cfc9_radius4)
        itemLayout.setPadding(0, 0, if ((position + 1) % 3 == 0) 0 else ConvertUtils.dp2px(14f), 0)
    }
}