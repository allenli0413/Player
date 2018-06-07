package com.liyh.player.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.liyh.player.model.MvAreaBean
import com.liyh.player.ui.fragment.MvChildFragment

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 18:39
 * Description:
 */
class MvPagerAdapter(val context: Context?, val list: List<MvAreaBean>?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val data = list?.get(position)
        val bundle = Bundle()
        bundle.putString("args", data?.code)
        val fragment = Fragment.instantiate(context, MvChildFragment::class.java.name, bundle)
        return fragment
    }

    override fun getCount(): Int = list?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence {
        return list?.get(position)?.name ?: ""
    }
}