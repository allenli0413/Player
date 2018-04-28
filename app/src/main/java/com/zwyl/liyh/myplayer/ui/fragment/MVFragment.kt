package com.zwyl.liyh.myplayer.ui.fragment

import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.adapter.MvPagerAdapter
import com.zwyl.liyh.myplayer.base.BaseFragment
import com.zwyl.liyh.myplayer.model.MvAreaBean
import com.zwyl.liyh.myplayer.presenter.impl.MvPresenterImpl
import com.zwyl.liyh.myplayer.view.MvView
import kotlinx.android.synthetic.main.fragment_mv.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:MVfragment
 */
class MVFragment : BaseFragment(), MvView {
    override fun onSuccess(result: List<MvAreaBean>) {
        val pagerAdapter = MvPagerAdapter(context, result, childFragmentManager)
        vp_fragment_mv.adapter = pagerAdapter
        tl_fragment_mv.setupWithViewPager(vp_fragment_mv)
    }

    override fun onError(msg: String?) {
        myToast("加载失败")
    }

    val presenter by lazy { MvPresenterImpl(this) }

    override fun initView(): View? = View.inflate(activity, R.layout.fragment_mv, null)

    override fun initData() {
        presenter.loadTabData()
    }

    override fun initListener() {

    }

}