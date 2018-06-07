package com.liyh.player.presenter.impl

import com.liyh.player.base.BaseListPresenter
import com.liyh.player.base.BaseListView
import com.liyh.player.http.HttpCallBack
import com.liyh.player.http.YuedanRequest
import com.liyh.player.model.YueDanBean
import com.liyh.player.presenter.interf.YuedanPresenter

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 12:08
 * Description:
 */
class YuedanPresenterImpl(var yuedanView: BaseListView<YueDanBean>?) : YuedanPresenter, HttpCallBack<YueDanBean> {
    override fun onSuccess(type: Int, result: YueDanBean) {
        if (type == BaseListPresenter.TYPE_INIT_OR_REFRESH) yuedanView?.onSuccess(result)
        else yuedanView?.onLoadMore(result)
    }

    override fun onError(type: Int, msg: String?) {
        yuedanView?.onError(msg)
    }

    override fun loadDatas() {
        YuedanRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).execute()
    }

    override fun loadMoreDatas(offset: Int) {
        YuedanRequest(BaseListPresenter.TYPE_LOAD_MORE, offset, this).execute()
    }

    override fun destroyView() {
        if (yuedanView != null) {
            yuedanView = null
        }
    }
}