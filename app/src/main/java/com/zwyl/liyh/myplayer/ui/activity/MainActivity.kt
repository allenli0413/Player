package com.zwyl.liyh.myplayer.ui.activity

import android.support.v7.widget.Toolbar
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.util.FragmentManagerUtil
import com.zwyl.liyh.myplayer.util.ToolBarManager
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
