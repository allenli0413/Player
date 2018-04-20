package com.zwyl.liyh.myplayer.ui.fragment

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.ui.activity.AboutActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 20:10
 * Description:
 */
class SettingFragment : PreferenceFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        when (key) {
            "clear_cache" -> toast("清楚缓存")
            "about" -> activity.startActivity<AboutActivity>()
//            "clear_cache"-> toast("清楚缓存")
//            "clear_cache"-> toast("清楚缓存")
            else -> toast("未知")
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}