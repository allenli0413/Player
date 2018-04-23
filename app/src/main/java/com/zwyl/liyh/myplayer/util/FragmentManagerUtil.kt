package com.zwyl.liyh.myplayer.util

import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseFragment
import com.zwyl.liyh.myplayer.ui.fragment.HomeFragment
import com.zwyl.liyh.myplayer.ui.fragment.MVFragment
import com.zwyl.liyh.myplayer.ui.fragment.VBangFragment
import com.zwyl.liyh.myplayer.ui.fragment.YueDanFragment

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:27
 * Description:fragmnet管理类 单例
 */
class FragmentManagerUtil private constructor() {
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MVFragment() }
    val vBangFragment by lazy { VBangFragment() }
    val yueDanFragment by lazy { YueDanFragment() }

    companion object {
        val instance by lazy { FragmentManagerUtil() }
    }

    /**
     * 根据tabId获取对应的fragment
     */
    fun getFragment(tabId: Int): BaseFragment? =
            when (tabId) {
                R.id.tab_home -> homeFragment
                R.id.tab_mv -> mvFragment
                R.id.tab_vbang -> vBangFragment
                R.id.tab_yuedan -> yueDanFragment
                else -> null
            }
}