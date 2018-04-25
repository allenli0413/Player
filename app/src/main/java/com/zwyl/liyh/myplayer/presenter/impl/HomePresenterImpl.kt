package com.zwyl.liyh.myplayer.presenter.impl

import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.base.BaseListPresenter
import com.zwyl.liyh.myplayer.base.BaseListView
import com.zwyl.liyh.myplayer.http.HomeRequest
import com.zwyl.liyh.myplayer.http.HttpCallBack
import com.zwyl.liyh.myplayer.presenter.interf.HomePresenter

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/23  Time: 18:24
 * Description:
 */
class HomePresenterImpl(var homeView: BaseListView<List<HomeItemBean>>?) : HomePresenter, HttpCallBack<List<HomeItemBean>> {

    /**
     * 成功回调
     */
    override fun onSuccess(type: Int, result: List<HomeItemBean>) {
        when (type) {
            BaseListPresenter.TYPE_INIT_OR_REFRESH -> homeView?.onSuccess(result)
            BaseListPresenter.TYPE_LOAD_MORE -> homeView?.onLoadMore(result)
        }
    }

    /**
     * 失败回调
     */
    override fun onError(type: Int, msg: String?) {
        homeView?.onError(msg)
    }

    /**
     * 初始化数据或者刷新
     */
    override fun loadDatas() {
        HomeRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).execute()
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreDatas(offset: Int) {
        HomeRequest(BaseListPresenter.TYPE_LOAD_MORE, offset, this).execute()
    }

    /**
     * 解绑presenter和view
     */
    override fun destroyView() {
        if (homeView != null) homeView = null
    }
}