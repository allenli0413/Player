package com.liyh.player.ui.activity

import android.support.v7.widget.Toolbar
import com.liyh.player.R
import com.liyh.player.base.BaseActivity
import com.liyh.player.util.ToolBarManager
import kotlinx.android.synthetic.main.layout_toobar.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 20:16
 * Description:关于页面
 */
class AboutActivity : BaseActivity(), ToolBarManager {
    override val toolBar: Toolbar by lazy { toolbar }

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        initToolBar("关于")
    }

}