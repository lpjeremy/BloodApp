package com.lpjeremy.libmodule.http.model

open class BaseResultJava<T> {
    val code: Int = -1
    val count: Int = 0
    val data: T? = null
    val msg: String = ""
}
