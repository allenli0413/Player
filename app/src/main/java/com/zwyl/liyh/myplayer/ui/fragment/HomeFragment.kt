package com.zwyl.liyh.myplayer.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.view.View
import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.adapter.HomeAdapter
import com.zwyl.liyh.myplayer.base.BaseFragment
import com.zwyl.liyh.myplayer.presenter.impl.HomePresenterImpl
import com.zwyl.liyh.myplayer.view.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.info

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:首页fragment
 */
class HomeFragment : BaseFragment(), HomeView {

    val mAdapter by lazy { HomeAdapter() }
    val presenter by lazy { HomePresenterImpl(this) }

    override fun initView(): View? = View.inflate(activity, R.layout.fragment_home, null)

    override fun initListener() {
        recl_fragment_home.layoutManager = LinearLayoutManager(activity)
        recl_fragment_home.adapter = mAdapter
        srl_fragment_home.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        srl_fragment_home.setOnRefreshListener {
            presenter.loadDatas()
        }
        recl_fragment_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_IDLE) {
                    val manager = recyclerView.layoutManager
                    if (manager is LinearLayoutManager) {
                        val position = manager.findLastVisibleItemPosition()
                        if (position == mAdapter.itemCount - 1 && position != 0) {
                            presenter.loadMoreDatas(mAdapter.itemCount)
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        //数据初始化
        presenter.loadDatas()
    }

    override fun onError(message: String?) {
        //隐藏刷新控件
        srl_fragment_home.isRefreshing = false
        myToast("加载数据失败：$message")
    }

    override fun onLoadSuccess(list: List<HomeItemBean>?) {
        //隐藏刷新控件
        srl_fragment_home.isRefreshing = false
        mAdapter.upDateDatas(list as ArrayList<HomeItemBean>)
    }

    override fun onLoadMore(list: List<HomeItemBean>?) {
        //隐藏刷新控件
        srl_fragment_home.isRefreshing = false
        mAdapter.loadMoreDatas(list as ArrayList<HomeItemBean>?)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
        info { "homeFragment销毁" }
    }
}