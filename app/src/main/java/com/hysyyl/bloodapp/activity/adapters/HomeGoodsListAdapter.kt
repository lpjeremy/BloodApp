package com.hysyyl.bloodapp.activity.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.activity.adapters.viewholder.HomeGoodsViewHolder
import com.hysyyl.bloodapp.model.Goods

class HomeGoodsListAdapter(goodsList: MutableList<Goods>) : RecyclerView.Adapter<HomeGoodsViewHolder>() {

    private val goodsData = goodsList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGoodsViewHolder {
        return HomeGoodsViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return goodsData.size
    }

    override fun onBindViewHolder(holder: HomeGoodsViewHolder, position: Int) {
        holder.setGoodsInfo(goodsData[position], position)
    }
}