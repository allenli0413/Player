package com.liyh.player.ui.fragment

import android.view.View
import com.liyh.player.R
import com.liyh.player.adapter.MvPagerAdapter
import com.liyh.player.base.BaseFragment
import com.liyh.player.model.MvAreaBean
import com.liyh.player.presenter.impl.MvPresenterImpl
import com.liyh.player.view.MvView
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}