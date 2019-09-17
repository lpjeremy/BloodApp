package com.hysyyl.bloodapp.activity.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.activity.adapters.viewholder.RechargeItemViewHolder
import com.hysyyl.bloodapp.model.RechargeConfig

/**
 * @desc:充值配置数据适配器
 * @date:2019/9/17 14:19
 * @auther:lp
 * @version:1.1.6
 */
class RechargeDataAdapter(rechargeList: MutableList<RechargeConfig>) : RecyclerView.Adapter<RechargeItemViewHolder>() {
    private val rechargeData = rechargeList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RechargeItemViewHolder {
        return RechargeItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return rechargeData.size
    }

    override fun onBindViewHolder(holder: RechargeItemViewHolder, position: Int) {
        holder.setRechargeItemData(rechargeData[position], position)
        holder.itemLayout.setOnClickListener {
            for (item in rechargeData) {
                item.checked = item.Id == rechargeData[position].Id
            }
            notifyDataSetChanged()
        }
    }
}