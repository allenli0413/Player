package com.liyh.player.util

import android.os.Environment
import java.io.File

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 03 日
 * @time  14 时 43 分
 * @descrip :歌词加载类
 */
object LyricLoader {
    val dir = File(Environment.getDataDirectory().path, "data/com.liyh.myplayer/lrc")
    fun loadLyricFile(display_name: String): File {
        return when (display_name) {
            "叶洛洛 - 我的将军啊 [mqms2].mp3",
            "排骨教主 - 庸人自扰 [mqms2].mp3",
            "Honor.mp3" -> File(dir, "${display_name.split(".")[0].toLowerCase()}.lrc")
            else -> File(dir, "geci.lrc")
        }

    }
}