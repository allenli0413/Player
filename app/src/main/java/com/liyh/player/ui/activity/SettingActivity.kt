package com.liyh.player.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.liyh.player.R
import com.liyh.player.base.BaseActivity
import com.liyh.player.util.ToolBarManager
import kotlinx.android.synthetic.main.layout_toobar.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 20:07
 * Description:设置页面
 */
class SettingActivity : BaseActivity(), ToolBarManager {
    override val toolBar: Toolbar by lazy { toolbar}
    override fun getLayoutId(): Int = R.layout.activity_setting
    override fun initView() {
        initSettingToolBar()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val isPush = sp.getBoolean("push", false)
        myToast("" + isPush)
    }
}