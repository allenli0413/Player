package com.zwyl.liyh.myplayer.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zwyl.liyh.myplayer.widget.LoadMoreView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 16:32
 * Description:
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW : View> : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {

    private val list = ArrayList<ITEMBEAN>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseListHolder {
        return when (viewType) {
            0 -> BaseListHolder(getItemView(parent?.context))
            else -> BaseListHolder(LoadMoreView(parent?.context))
        }
    }

    /**
     * 获取条目的view
     */
    abstract fun getItemView(context: Context?): ITEMVIEW?

    override fun getItemCount(): Int = if (list.size == 0) 0 else list.size + 1

    override fun onBindViewHolder(holder: BaseListHolder?, position: Int) {
        if (holder?.itemViewType == 0) {
            val data = list.get(position)
            val itemView = holder.itemView as ITEMVIEW
            refreshItemView(itemView, data)
        }
    }

    abstract fun refreshItemView(itemView: ITEMVIEW, data: ITEMBEAN)


    /**
     * 刷新列表
     */

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) 1 else 0
    }

    /**
     * 刷新数据
     */
    fun upDateDatas(newList: List<ITEMBEAN>?) {
        newList?.let {
            list.clear()
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * 加载更多
     */
    fun loadMoreDatas(newList: List<ITEMBEAN>?) {
        newList?.let {
            list.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class BaseListHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}