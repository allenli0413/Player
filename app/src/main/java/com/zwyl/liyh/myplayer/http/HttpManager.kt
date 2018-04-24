package com.zwyl.liyh.myplayer.http

import com.zwyl.liyh.myplayer.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/24  Time: 10:19
 * Description:发送请求的实例
 */
class HttpManager private constructor() {

    val client by lazy { OkHttpClient() }

    companion object {
        val manager by lazy { HttpManager() }
    }

    /**
     * 发送网络请求
     */
    fun <RESULT> sendRequest(req: MRequest<RESULT>) {

        val request = Request.Builder()
                .get()
                .url(req.url)
                .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程执行
             */
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable {
                    //回到View层处理
                    req.callBack.onError(req.type, e?.message)
                })
            }

            /**
             * 子线程执行
             */
            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                val parseResult = req.parseJsonResult(result)
                ThreadUtil.runOnMainThread(Runnable {
                    //回到View层处理
                    req.callBack.onSuccess(req.type, parseResult)
                })
            }
        })
    }
}