package com.zwyl.liyh.myplayer.util

import android.support.v7.widget.Toolbar
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.ui.activity.SettingActivity
import org.jetbrains.anko.startActivity

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 19:32
 * Description:
 */
interface ToolBarManager {
    val toolBar: Toolbar
    /**
     * 处理mian界面的toolbar
     */
    fun initMainToolBar() {
        toolBar.setTitle("影音播放器")
        toolBar.inflateMenu(R.menu.main)
        toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.toolbar_setting -> toolBar.context.startActivity<SettingActivity>()
            }
            true
        }
    }

    /**
     * 处理设置界面的toolbar
     */
    fun initSettingToolBar() {
        toolBar.setTitle("设置")
    }

    fun initToolBar(msg: String) {
        toolBar.setTitle(msg)
    }
}