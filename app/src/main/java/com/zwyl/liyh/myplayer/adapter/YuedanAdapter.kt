package com.zwyl.liyh.myplayer.adapter

import YueDanBean
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zwyl.liyh.myplayer.widget.LoadMoreView
import com.zwyl.liyh.myplayer.widget.YueDanItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 22:17
 * Description:悦单页面条目适配器
 */
class YuedanAdapter : RecyclerView.Adapter<YuedanAdapter.YuedanHolder>() {

    val list = ArrayList<YueDanBean.PlayListsBean>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): YuedanHolder {
        return when (viewType) {
            0 -> YuedanHolder(YueDanItemView(parent?.context))
            else -> YuedanHolder(LoadMoreView(parent?.context))
        }
    }

    override fun getItemCount(): Int = if (list.size == 0) 0 else list.size + 1

    override fun onBindViewHolder(holder: YuedanHolder?, position: Int) {
        val itemView = holder?.itemView
        if (itemView is YueDanItemView) {
            val data = list.get(position)
            itemView.setData(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) 1 else 0
    }

    /**
     * 刷新数据
     */
    fun upDateDatas(newList: ArrayList<YueDanBean.PlayListsBean>?) {
        newList?.let {
            list.clear()
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * 加载更多
     */
    fun loadMoreDatas(newList: ArrayList<YueDanBean.PlayListsBean>?) {
        newList?.let {
            list.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class YuedanHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}