package com.zwyl.liyh.myplayer.util

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 17 日
 * @time  18 时 34 分
 * @descrip :处理时间毫秒值
 */
object StringUtil {
    val HOUR = 60 * 60 * 1000
    val MIN = 60 * 1000
    val SEC = 1000
    fun handleTime(millisecond: Int): String {
        val hour = millisecond / HOUR
        val min = millisecond % HOUR / MIN
        val sec = millisecond % MIN / SEC
        var result = ""
        if (hour == 0) {
            result = String.format("%02d:%02d", min, sec)
        } else {
            result = String.format("%02d:%02d:%02d", hour, min, sec)
        }
        return result
    }
}