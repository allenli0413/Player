package com.zwyl.liyh.myplayer.view

import com.itheima.player.model.bean.HomeItemBean

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/23  Time: 18:23
 * Description:home界面和presenter交互
 */
interface HomeView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun onLoadSuccess(list: List<HomeItemBean>?)

    /**
     * 加载更多成功
     */
    fun onLoadMore(list: List<HomeItemBean>?)
}