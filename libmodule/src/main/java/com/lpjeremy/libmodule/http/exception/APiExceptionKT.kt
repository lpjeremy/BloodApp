package com.lpjeremy.libmodule.http.exception

class APiExceptionKT(codeParams: Int?, msgParams: String?) : Exception() {
    var code: Int = 0
    var msg: String = ""

    init {
        if (codeParams != null && msgParams != null) {
            this.code = codeParams
            this.msg = msgParams
        }
    }
}