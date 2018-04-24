package com.zwyl.liyh.myplayer.presenter.impl

import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.http.HomeRequest
import com.zwyl.liyh.myplayer.http.HttpCallBack
import com.zwyl.liyh.myplayer.presenter.interf.HomePresenter
import com.zwyl.liyh.myplayer.view.HomeView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/23  Time: 18:24
 * Description:
 */
class HomePresenterImpl(var homeView: HomeView?) : HomePresenter, HttpCallBack<List<HomeItemBean>> {

    /**
     * 成功回调
     */
    override fun onSuccess(type: Int, result: List<HomeItemBean>) {
        when (type) {
            HomePresenter.TYPE_INIT_OR_REFRESH -> homeView?.onLoadSuccess(result)
            HomePresenter.TYPE_LOAD_MORE -> homeView?.onLoadMore(result)
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
    fun loadDatas() {
        HomeRequest(HomePresenter.TYPE_INIT_OR_REFRESH, 0, this).execute()
    }

    /**
     * 加载更多数据
     */
    fun loadMoreDatas(offset: Int) {
        HomeRequest(HomePresenter.TYPE_LOAD_MORE, offset, this).execute()
    }

    /**
     * 解绑presenter和view
     */
    fun destroyView() {
        if (homeView != null) homeView = null
    }
}