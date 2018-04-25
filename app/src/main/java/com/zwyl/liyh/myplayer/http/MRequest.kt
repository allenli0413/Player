package com.zwyl.liyh.myplayer.http

import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.lang.reflect.ParameterizedType

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/23  Time: 21:02
 * Description:所有请求的基类
 */
open class MRequest<RESULT>(val type: Int, val url: String, val callBack: HttpCallBack<RESULT>) : AnkoLogger {

    /**
     * 执行网络请求
     */
    fun execute() {
        HttpManager.manager.sendRequest(this)
    }

    /**
     * 解析json为对象
     */
    fun parseJsonResult(result: String?): RESULT {
        info { "返回json：$result" }
        val type = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val gson = Gson()
        val parResult = gson.fromJson<RESULT>(result, type)
        return parResult
    }
}