package com.hysyyl.bloodapp.model.request

data class VideoRequest(
    val ClientType: Int,
    val length: Int,
    val ordertext: List<Ordertext>,
    val searchtext: String,
    val start: Int
)

data class Ordertext(
    val column: String,
    val dir: String
)