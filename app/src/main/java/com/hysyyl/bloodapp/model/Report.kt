package com.hysyyl.bloodapp.model

/**
 * @desc:报告
 * @date:2019/8/27 9:38
 * @auther:lp
 * @version:1.1.6
 */
data class Report(
    val Contacts: String,
    val ContactsPhone: String,
    val CreateTime: String,
    val CustomerName: String,
    val Goods: String,
    val IsAbnormal: Boolean,
    val OrderId: String,
    val OrderNo: String,
    val PatientAgeMonth: String,
    val PatientAgeYear: String,
    val PatientGender: String,
    val PatientMobile: String,
    val PatientName: String,
    val ReportState: String
)