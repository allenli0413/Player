package com.zwyl.liyh.myplayer.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.zwyl.liyh.myplayer.ui.fragment.MvPlayerFragment

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/28  Time: 18:55
 * Description:
 */
class MvPlayerViewPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return MvPlayerFragment()
    }

    override fun getCount(): Int = 3
}