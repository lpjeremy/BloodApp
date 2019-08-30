package com.hysyyl.bloodapp.model

/**
 * 账户
 */
class Account {
    /**
     *  余额
     */
    var Balance: Double = 0.0
    /**
     * 可用优惠券数量
     */
    var CouponCount: Int = 0
    /**
     * 支付模式
     */
    var PayModel: String = ""

    var readOnly: Boolean = false
}