package com.zwyl.liyh.myplayer.presenter.impl

import com.zwyl.liyh.myplayer.base.BaseListPresenter
import com.zwyl.liyh.myplayer.base.BaseListView
import com.zwyl.liyh.myplayer.http.HttpCallBack
import com.zwyl.liyh.myplayer.http.MvListRequest
import com.zwyl.liyh.myplayer.model.MvPagerBean
import com.zwyl.liyh.myplayer.presenter.interf.MvListPresenter

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 22:11
 * Description:
 */
class MvListPresenterImpl(val code: String, var mvListView: BaseListView<MvPagerBean>?) : MvListPresenter, HttpCallBack<MvPagerBean> {
    override fun onSuccess(type: Int, result: MvPagerBean) {
        if (type == BaseListPresenter.TYPE_INIT_OR_REFRESH) mvListView?.onSuccess(result)
        else mvListView?.onLoadMore(result)
    }

    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun loadDatas() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, code, 0, this).execute()
    }

    override fun loadMoreDatas(offset: Int) {
        MvListRequest(BaseListPresenter.TYPE_LOAD_MORE, code, offset, this).execute()
    }

    override fun destroyView() {
        if (mvListView != null) mvListView = null
    }
}