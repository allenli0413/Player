package com.zwyl.liyh.myplayer.ui.activity

import android.support.v7.widget.Toolbar
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.util.ToolBarManager
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