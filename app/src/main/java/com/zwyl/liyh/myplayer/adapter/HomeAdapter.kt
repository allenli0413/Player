package com.zwyl.liyh.myplayer.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.widget.HomeItemView
import com.zwyl.liyh.myplayer.widget.LoadMoreView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 22:17
 * Description:
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    val list = ArrayList<HomeItemBean>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        return when (viewType) {
            0 -> HomeHolder(HomeItemView(parent?.context))
            else -> HomeHolder(LoadMoreView(parent?.context))
        }
    }

    override fun getItemCount(): Int = if (list.size == 0) 0 else list.size + 1

    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {
        if (position == list.size) return
        val data = list.get(position)
        val itemView = holder?.itemView as HomeItemView
        itemView.setData(data)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) 1 else 0
    }

    /**
     * 刷新数据
     */
    fun upDateDatas(newList: ArrayList<HomeItemBean>?) {
        newList?.let {
            list.clear()
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * 加载更多
     */
    fun loadMoreDatas(newList: ArrayList<HomeItemBean>?) {
        newList?.let {
            list.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class HomeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}