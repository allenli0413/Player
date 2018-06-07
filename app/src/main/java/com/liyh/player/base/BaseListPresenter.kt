package com.liyh.player.base

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 16:31
 * Description:列表p层基类
 */
interface BaseListPresenter {
    companion object {
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }

    /**
     * 初始化数据或者刷新
     */
    fun loadDatas()

    /**
     * 加载更多数据
     */
    fun loadMoreDatas(offset: Int)

    /**
     * 解绑presenter和view
     */
    fun destroyView()
}