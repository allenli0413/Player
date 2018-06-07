package com.liyh.player.ui.fragment

import com.liyh.player.adapter.HomeAdapter
import com.liyh.player.base.BaseListAdapter
import com.liyh.player.base.BaseListFragment
import com.liyh.player.base.BaseListPresenter
import com.liyh.player.model.HomeItemBean
import com.liyh.player.presenter.impl.HomePresenterImpl
import com.liyh.player.widget.HomeItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 escription:首页fragment
 */
class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>() {
    override fun getList(result: List<HomeItemBean>?): List<HomeItemBean>? = result

    override fun getSelfAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> = HomeAdapter()

    override fun getSelfPresenter(): BaseListPresenter = HomePresenterImpl(this)

}