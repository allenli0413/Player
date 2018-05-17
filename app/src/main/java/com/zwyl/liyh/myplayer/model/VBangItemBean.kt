package com.zwyl.liyh.myplayer.model

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/5/9  Time: 12:23
 * Description:V榜列表条目
 */
class VBangItemBean(var data: String, var size: Long, var displayName: String, var artist: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(data)
        writeLong(size)
        writeString(displayName)
        writeString(artist)
    }

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

        fun getAudioBeans(cursor: Cursor?): ArrayList<VBangItemBean> {
            //创建集合
            val list = arrayListOf<VBangItemBean>()
            //判断cursor是否为空
            cursor?.let {
                //将游标移动到-1
                it.moveToPosition(-1)
                while (it.moveToNext()) {
                    val bean = getVBandItemBean(it)
                    list.add(bean)
                }
            }
            return list
        }

        @JvmField
        val CREATOR: Parcelable.Creator<VBangItemBean> = object : Parcelable.Creator<VBangItemBean> {
            override fun createFromParcel(source: Parcel): VBangItemBean = VBangItemBean(source)
            override fun newArray(size: Int): Array<VBangItemBean?> = arrayOfNulls(size)
        }
    }
}