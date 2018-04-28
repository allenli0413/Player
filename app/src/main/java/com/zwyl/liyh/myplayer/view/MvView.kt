package com.zwyl.liyh.myplayer.view

import com.zwyl.liyh.myplayer.model.MvAreaBean

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 22:29
 * Description:mv界面View层
 */
interface MvView {
    fun onSuccess(result: List<MvAreaBean>)
    fun onError(msg: String?)
}