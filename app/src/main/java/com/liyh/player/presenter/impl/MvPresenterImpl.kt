package com.liyh.player.presenter.impl

import com.liyh.player.http.HttpCallBack
import com.liyh.player.http.MvAreaRequest
import com.liyh.player.model.MvAreaBean
import com.liyh.player.presenter.interf.MvPresenter
import com.liyh.player.view.MvView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 22:31
 * Description:
 */
class MvPresenterImpl(var mvView: MvView?) : MvPresenter, HttpCallBack<List<MvAreaBean>> {

    override fun onSuccess(type: Int, result: List<MvAreaBean>) {
        mvView?.onSuccess(result)
    }

    override fun onError(type: Int, msg: String?) {
        mvView?.onError(msg)
    }

    /**
     * 请求tab标签的数据
     */
    override fun loadTabData() {
        MvAreaRequest(this).execute()
    }
    fun onDestroy() {
        if (mvView != null) {
            mvView = null
        }
    }
}