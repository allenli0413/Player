package com.zwyl.liyh.myplayer.ui.fragment

import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.adapter.HomeAdapter
import com.zwyl.liyh.myplayer.base.BaseListAdapter
import com.zwyl.liyh.myplayer.base.BaseListFragment
import com.zwyl.liyh.myplayer.base.BaseListPresenter
import com.zwyl.liyh.myplayer.presenter.impl.HomePresenterImpl
import com.zwyl.liyh.myplayer.widget.HomeItemView

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