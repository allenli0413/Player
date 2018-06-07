package com.liyh.player.adapter

import android.content.Context
import com.liyh.player.base.BaseListAdapter
import com.liyh.player.model.VideosBean
import com.liyh.player.widget.MvItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 22:08
 * Description:
 */
class MvListAdapter : BaseListAdapter<VideosBean, MvItemView>() {
    override fun getItemView(context: Context?): MvItemView? = MvItemView(context)

    override fun refreshItemView(itemView: MvItemView, data: VideosBean) {
        itemView.setData(data)
    }


}