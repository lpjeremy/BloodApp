package com.hysyyl.bloodapp.activity.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.model.Goods

class HomeGoodsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_home_goods_layout, parent, false)
) {
    private val txtName = itemView.findViewById<TextView>(R.id.txtHomeGoodsName)
    private val txtPrice = itemView.findViewById<TextView>(R.id.txtHomeGoodsPrice)
    private val txtOldPrice = itemView.findViewById<TextView>(R.id.txtHomeGoodsOldPrice)
    private val txtContent = itemView.findViewById<TextView>(R.id.txtHomeGoodsContent)
    private val txtDescrbe = itemView.findViewById<TextView>(R.id.txtHomeGoodsDescrbe)
    private val txtMoney = itemView.findViewById<TextView>(R.id.txtHomeGoodsMoney)
    private val imgAddCart = itemView.findViewById<ImageView>(R.id.imgHomeAddToCart)


    fun setGoodsInfo(goods: Goods,position:Int) {
        txtName.text = goods.Name+" "+position
        txtContent.text = goods.Content
        txtDescrbe.text = goods.Describe
        txtPrice.text = "￥" + goods.Price.toString()
        txtOldPrice.text = "￥" + goods.OldPrice.toString()
        txtMoney.text = "（￥" + (goods.OldPrice - goods.Price).toString() + ")"
    }

}