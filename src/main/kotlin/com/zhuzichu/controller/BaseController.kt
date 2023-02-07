package com.zhuzichu.controller

import com.zhuzichu.error.R
import spark.Request
import spark.Response

abstract class BaseController {

    abstract  fun path():String

    abstract fun handleRequest(request: Request, response: Response): R<*>

    fun handleError(e: Exception): R<*>{
        return R.error(msg = e.message ?: ("MailService error：" + e.javaClass.name))
    }

}