package com.zwyl.liyh.myplayer.presenter.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.presenter.interf.HomePresenter
import com.zwyl.liyh.myplayer.util.ThreadUtil
import com.zwyl.liyh.myplayer.util.URLProviderUtils
import com.zwyl.liyh.myplayer.view.HomeView
import okhttp3.*
import java.io.IOException

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/23  Time: 18:24
 * Description:
 */
class HomePresenterImpl(var homeView: HomeView) : HomePresenter {

    /**
     * 初始化数据或者刷新
     */
    fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
                .get()
                .url(path)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable {
                    //回到View层处理
                    homeView.onError(e?.message)
                    //隐藏刷新控件
//                        srl_fragment_home . isRefreshing = false
                })
//            myToast("网络请求失败")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                ThreadUtil.runOnMainThread(Runnable {
                    //回到View层处理
                    homeView.onLoadSuccess(list)
                })
            }
        })
    }

    /**
     * 加载更多数据
     */
    fun loadMoreDatas(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
                .get()
                .url(path)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable {
                    //回到View层处理
                    homeView.onError(e.toString())
                })
            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                ThreadUtil.runOnMainThread(Runnable {
                    //回到View层处理
                    homeView.onLoadMore(list)
                })
            }

        })
    }
}