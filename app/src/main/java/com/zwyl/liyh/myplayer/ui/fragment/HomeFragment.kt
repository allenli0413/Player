package com.zwyl.liyh.myplayer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.adapter.HomeAdapter
import com.zwyl.liyh.myplayer.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:首页fragment
 */
class HomeFragment : BaseFragment() {
    override fun initView(): View? = View.inflate(activity, R.layout.fragment_home, null)
    override fun initListener() {
        recl_fragment_home.layoutManager = LinearLayoutManager(activity)
        recl_fragment_home.adapter = HomeAdapter()
    }
}