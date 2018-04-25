package com.zwyl.liyh.myplayer.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.zwyl.liyh.myplayer.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 16:17
 * Description:包含下拉刷新上拉加载更多的列表fragment基类
 */
abstract class BaseListFragment<RESULT, ITEMBEAN, ITEMVIEW : View> : BaseFragment(), BaseListView<RESULT> {
    override fun onError(msg: String?) {
        myToast("网络加载失败")
    }

    override fun onSuccess(result: RESULT?) {
        myToast("获取数据成功")
        srl_fragment_home.isRefreshing = false
        mAdapter.upDateDatas(getList(result))
    }


    override fun onLoadMore(result: RESULT?) {
        srl_fragment_home.isRefreshing = false
        mAdapter.loadMoreDatas(getList(result))
    }

    val mAdapter by lazy { getSelfAdapter() }
    val presenter by lazy { getSelfPresenter() }

    override fun initView(): View? = View.inflate(activity, R.layout.fragment_home, null)
    override fun initListener() {
        recl_fragment_home.layoutManager = LinearLayoutManager(activity)
        recl_fragment_home.adapter = mAdapter
        srl_fragment_home.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        srl_fragment_home.setOnRefreshListener { presenter.loadDatas() }
        recl_fragment_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val manager = recyclerView?.layoutManager
                    if (manager is LinearLayoutManager) {
                        val position = manager.findLastVisibleItemPosition()
                        if (position == mAdapter.itemCount - 1) {
                            presenter.loadMoreDatas(mAdapter.itemCount - 1)
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter.loadDatas()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
    }

    /**
     * 通过网络请求回来结果的数据获取列表需要的集合
     */
    abstract fun getList(result: RESULT?): List<ITEMBEAN>?

    /**
     * 获取适配器
     */
    abstract fun getSelfAdapter(): BaseListAdapter<ITEMBEAN, ITEMVIEW>

    /**
     * 获取presenter
     */
    abstract fun getSelfPresenter(): BaseListPresenter

}