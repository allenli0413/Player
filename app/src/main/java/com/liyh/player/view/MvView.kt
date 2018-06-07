package com.liyh.player.view

import com.liyh.player.model.MvAreaBean

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