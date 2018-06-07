package com.liyh.player.http

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/24  Time: 10:13
 * Description:网络请求的回调
 */
interface HttpCallBack<RESULT> {

    fun onSuccess(type: Int, result: RESULT)
    fun onError(type: Int, msg: String?)
}