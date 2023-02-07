package com.zhuzichu.error

data class R<T>(
    var errorCode: Int = 1,
    var errorMsg: String? = null,
    var data: T? = null
) {
    companion object {
        fun error(code: Int = 500, msg: String = "服务错误"): R<String> {
            return R(500, msg)
        }

        fun <T> success(data: T? = null): R<T> {
            return R(1, data = data)
        }

    }
}