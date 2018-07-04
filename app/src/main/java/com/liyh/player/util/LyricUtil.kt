package com.liyh.player.util

import com.liyh.player.model.LyricBean
import java.io.File
import java.nio.charset.Charset

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 03 日
 * @time  13 时 56 分
 * @descrip :歌词解析类
 */

object LyricUtil{

    /**
     * 解析歌词文件
     */
    fun parseLrc(file: File): ArrayList<LyricBean> {
        //创建集合
        val lrcList = ArrayList<LyricBean>()
       //判断文件是否存在
        if (file.exists()) {
            val lines = file.readLines(charset = Charset.forName("utf-8"))
            for (line in lines) {
                val list = parseLine(line)
                lrcList.addAll(list)
            }
        } else {
            lrcList.add(LyricBean(0, "歌词加载错误！！！"))
            lrcList.add(LyricBean(0, "..."))
        }
        //按开始时间排序
        lrcList.sortBy { it.startTime }
        return lrcList
    }



    /**
     * 解析单行歌词
     * [00:00.23][00:00.23]我的将军啊（Cover：半阳）
     */
    private fun parseLine(line: String): ArrayList<LyricBean> {
        val list = ArrayList<LyricBean>()
        val split = line.split("]")
        //获取歌词内容
        val content = split[split.size -1]
        for (index in 0 until split.size -1) {
            //获取开始时间
            val startTime = parseTime(split[index])
            list.add(LyricBean(startTime, content))
        }
        return list
    }

    /**
     * 解析时间
     * [00:00.23
     */
    private fun parseTime(s: String): Int {
        var hour = 0
        var min = 0
        var sec = 0F
        val substring = s.substring(1)
        val split = substring.split(":")
        if (split.size == 3) {
            hour = split[0].toInt() * 60 * 60 * 1000
            min = split[1].toInt() * 60 * 1000
            sec = split[2].toFloat() * 1000
        } else {
            min = split[0].toInt() * 60 * 1000
            sec = split[1].toFloat() * 1000
        }
        return (hour + min + sec).toInt()
    }
}