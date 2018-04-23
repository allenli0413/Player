package com.zwyl.liyh.myplayer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.adapter.HomeAdapter
import com.zwyl.liyh.myplayer.base.BaseFragment
import com.zwyl.liyh.myplayer.util.URLProviderUtils
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.jetbrains.anko.info
import java.io.IOException

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:首页fragment
 */
class HomeFragment : BaseFragment() {
    override fun initView(): View? = View.inflate(activity, R.layout.fragment_home, null)
    override fun initListener() {
        recl_fragment_home.layoutManager = LinearLayoutManager(activity)
        recl_fragment_home.adapter = HomeAdapter()
    }

    override fun initData() {
        //数据初始化
        loadDatas()
    }

    private fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
                .get()
                .url(path)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                info { "网络请求失败${Thread.currentThread().name}" }
                myToast("网络请求失败")
            }

            override fun onResponse(call: Call?, response: Response?) {
                info { "网络请求成功${Thread.currentThread().name}" }
                myToast("网络请求成功")
            }

        })
    }
}