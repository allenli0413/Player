package com.zwyl.liyh.myplayer.base

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 16:28
 * Description:列表的View层基类
 */
interface BaseListView<RESULT> {
    fun onError(msg: String?)
    fun onSuccess(result: RESULT?)
    fun onLoadMore(result: RESULT?)
}