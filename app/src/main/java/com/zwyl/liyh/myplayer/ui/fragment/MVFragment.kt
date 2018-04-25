package com.zwyl.liyh.myplayer.ui.fragment

import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseFragment
import com.zwyl.liyh.myplayer.presenter.impl.MvPresenterImpl
import com.zwyl.liyh.myplayer.view.MvView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:MVfragment
 */
class MVFragment : BaseFragment(), MvView {

    val presenter by lazy { MvPresenterImpl(this) }

    override fun initView(): View? = View.inflate(activity, R.layout.fragment_mv, null)

    override fun initData() {

    }

    override fun initListener() {

    }

}