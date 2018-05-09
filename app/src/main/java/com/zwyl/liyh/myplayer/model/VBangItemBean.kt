package com.zwyl.liyh.myplayer.model

import android.database.Cursor
import android.provider.MediaStore

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/5/9  Time: 12:23
 * Description:V榜列表条目
 */
class VBangItemBean(var data: String, var size: Long, var displayName: String, var artist: String) {
    companion object {
        /**
         * 根据特定位置上的cursor获取bean
         */
        fun getVBandItemBean(cursor: Cursor?): VBangItemBean {
            //创建bean
            val itemBean = VBangItemBean("", 0, "", "")
            //判断cursor不为空，并赋值给bean
            cursor?.let {
                itemBean.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                itemBean.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                itemBean.displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                itemBean.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
            }
            return itemBean
        }
    }
}