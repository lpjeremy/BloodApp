package com.hysyyl.bloodapp.model

/**
 * {
"ID": 9412,
"No": "dm360aav",
"Name": "脱出するのは犯した後で～闭じ_められた",
"IsVip": 1,
"IsNeedLogin": 1,
"Disabled": 0,
"TypeID": 13,
"Url": "",
"CoverImgUrl": "http://img.abhxc.com/6dm/dm360aavq.jpg",
"SeeCount": 7979,
"CollectionCount": 621,
"Point": 0.0,
"Length": 1506,
"Tags": "甜美,巨乳,口交,后入,女上位,内射,高难度",
"TypeName": "动漫",
"IsHot": 0,
"IsRecomend": 0,
"AddTime": "2019-09-23 23:07:45"
}
 */
data class Video(
    val AddTime: String,
    val CollectionCount: Int,
    val CoverImgUrl: String,
    val Disabled: Int,
    val ID: Int,
    val IsHot: Int,
    val IsNeedLogin: Int,
    val IsRecomend: Int,
    val IsVip: Int,
    val Length: Int,
    val Name: String,
    val No: String,
    val Point: Double,
    val SeeCount: Int,
    val Tags: String,
    val TypeID: Int,
    val TypeName: String,
    val Url: String
)
