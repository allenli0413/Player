package com.liyh.player.ui.fragment

import com.liyh.player.adapter.YuedanAdapter
import com.liyh.player.base.BaseListAdapter
import com.liyh.player.base.BaseListFragment
import com.liyh.player.base.BaseListPresenter
import com.liyh.player.model.YueDanBean
import com.liyh.player.presenter.impl.YuedanPresenterImpl
import com.liyh.player.widget.YueDanItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:悦单fragment
 */
class YueDanFragment : BaseListFragment<YueDanBean, YueDanBean.PlayListsBean, YueDanItemView>(){

    override fun getList(result: YueDanBean?): List<YueDanBean.PlayListsBean>? = result?.playLists

    override fun getSelfAdapter(): BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView> = YuedanAdapter()

    override fun getSelfPresenter(): BaseListPresenter = YuedanPresenterImpl(this)

}