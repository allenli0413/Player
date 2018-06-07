package com.liyh.player.ui.activity

import android.support.v7.widget.Toolbar
import com.liyh.player.R
import com.liyh.player.base.BaseActivity
import com.liyh.player.util.FragmentManagerUtil
import com.liyh.player.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toobar.*

class MainActivity : BaseActivity(), ToolBarManager {

    override val toolBar: Toolbar by lazy { toolbar }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        initMainToolBar()
        bottomBar.setOnTabSelectListener {
            //it代表tabId
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_main_container, FragmentManagerUtil.instance.getFragment(it), it.toString())
            transaction.commit()
        }
    }

}
