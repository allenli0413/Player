package com.zwyl.liyh.myplayer.util

import android.database.Cursor

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/5/3  Time: 19:00
 * Description:打印cursor的工具类
 */
object CursorUtil {

    fun logCursor(cursor: Cursor?) {
        cursor?.let {
            it.moveToPosition(-1)
            while (it.moveToNext()) {
                for (i in 0 until it.columnCount) {
                    println("key = ${it.getColumnName(i)}, value = ${it.getString(i)}")
                }
            }

        }
    }
}