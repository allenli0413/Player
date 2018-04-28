package com.zwyl.liyh.myplayer.presenter.impl

import com.zwyl.liyh.myplayer.http.HttpCallBack
import com.zwyl.liyh.myplayer.http.MvAreaRequest
import com.zwyl.liyh.myplayer.model.MvAreaBean
import com.zwyl.liyh.myplayer.presenter.interf.MvPresenter
import com.zwyl.liyh.myplayer.view.MvView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 22:31
 * Description:
 */
class MvPresenterImpl(val mvView: MvView) : MvPresenter,HttpCallBack<List<MvAreaBean>> {

    override fun onSuccess(type: Int, result: List<MvAreaBean>) {
        mvView.onSuccess(result)
    }

    override fun onError(type: Int, msg: String?) {
        mvView.onError(msg)
    }

    /**
     * 请求tab标签的数据
     */
    override fun loadTabData() {
        MvAreaRequest(this).execute()
    }
}