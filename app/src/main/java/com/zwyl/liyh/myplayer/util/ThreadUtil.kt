package com.zwyl.liyh.myplayer.util

import android.os.Handler
import android.os.Looper

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/23  Time: 14:44
 * Description:线程工具类 单例
 */
object ThreadUtil {

    val handler = Handler(Looper.getMainLooper())

    /**
     * 在主线程执行
     */
    fun runOnMainThread(runnable: Runnable) {
        handler.post(runnable)
    }
}