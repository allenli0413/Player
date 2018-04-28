package com.zwyl.liyh.myplayer.ui.fragment

import com.zwyl.liyh.myplayer.adapter.YuedanAdapter
import com.zwyl.liyh.myplayer.base.BaseListAdapter
import com.zwyl.liyh.myplayer.base.BaseListFragment
import com.zwyl.liyh.myplayer.base.BaseListPresenter
import com.zwyl.liyh.myplayer.model.YueDanBean
import com.zwyl.liyh.myplayer.presenter.impl.YuedanPresenterImpl
import com.zwyl.liyh.myplayer.widget.YueDanItemView

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