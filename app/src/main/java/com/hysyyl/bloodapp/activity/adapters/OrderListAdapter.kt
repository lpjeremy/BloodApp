package com.hysyyl.bloodapp.activity.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.hysyyl.bloodapp.activity.adapters.viewholder.OrderViewHolder
import com.hysyyl.bloodapp.model.Order
/**
 * @desc:订单列表数据适配器
 * @date:2019/9/16 14:04
 * @auther:lp
 * @version:1.1.6
 */
class OrderListAdapter : PagedListAdapter<Order, OrderViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(parent)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.setOrderInfo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.Id == newItem.Id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem==newItem
            }
        }
    }
}