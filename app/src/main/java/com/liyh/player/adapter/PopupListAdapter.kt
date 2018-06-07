package com.liyh.player.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.liyh.player.model.VBangItemBean
import com.liyh.player.widget.PopWindowItemView

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 28 日
 * @time  11 时 56 分
 * @descrip :v榜界面popupwindow列表的适配器
 */
class PopupListAdapter(val list: ArrayList<VBangItemBean>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): PopWindowItemView? {
        var itemView: PopWindowItemView =
        if (convertView == null) PopWindowItemView(parent?.context)
        else convertView as PopWindowItemView
        val itemModel = list[position]
        itemView.setData(itemModel)
        return itemView
    }

    override fun getItem(position: Int): VBangItemBean = list.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

}