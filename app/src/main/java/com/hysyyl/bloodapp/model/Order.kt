package com.hysyyl.bloodapp.model

data class Order(
    val AgeMonth: Int,
    val AgeYear: Int,
    val CommonRebateRate: Double,
    val CreateTime: String,
    val CreateTimeStr: String,
    val DueAmount: Double,
    val Gender: String,
    val GoodsName: String,
    val Id: String,
    val IsBarcodeFill: Boolean,
    val IsFormComplete: Boolean,
    val IsSampleAbnormal: Boolean,
    val Name: String,
    val OrderNo: String,
    val OrderStatus: Int,
    val OrderStatusRemark: String,
    val OrderStatusStr: String,
    val OrderType: Int,
    val OrderTypeStr: String,
    val PayModel: Int,
    val PayModelStr: String,
    val PayStatus: Int,
    val PayStatusStr: String,
    val ServiceStatus: Int,
    val ServiceStatusStr: String
)