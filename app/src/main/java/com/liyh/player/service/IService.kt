package com.liyh.player.service

import com.liyh.player.model.VBangItemBean

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 16 日
 * @time  18 时 58 分
 * @descrip :
 */
interface IService {
//    fun playItem()
    fun start()
    fun pause()
    fun stop()
    fun prev()
    fun next()
    fun updatePlayStatus():Boolean?
    fun isPlaying():Boolean?
    fun notfityUpdateUi()
    fun getDuration(): Int?
    fun getProgress(): Int?
    fun setProgress(progress: Int)
    fun setPlayMode(currentMode: Int)
    fun getCurMode(): Int
    abstract fun getList(): ArrayList<VBangItemBean>?
    abstract fun playPosition(position: Int)
}