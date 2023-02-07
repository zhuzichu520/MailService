package com.zhuzichu

import com.google.gson.Gson
import com.zhuzichu.controller.BaseController
import com.zhuzichu.controller.LoginController
import spark.Spark.*


object App {

    private var url = ""
    private val controllerList = mutableListOf<BaseController>()
    private val gson = Gson()

    init {
        controllerList.add(LoginController())
    }

    fun run(args: Array<String>, port: Int) {
        port(port)
        controllerList.forEach {
            get(it.path()) { request, response ->
                try {
                    gson.toJson(it.handleRequest(request, response))
                } catch (e: Exception) {
                    gson.toJson(it.handleError(e))
                }
            }
        }
        url = "http://localhost:${port}"
        println("MailService init success，url = $url")
        controllerList.forEach {
            print("path=${it.path()}，url=${url+it.path()}")
        }
    }

}