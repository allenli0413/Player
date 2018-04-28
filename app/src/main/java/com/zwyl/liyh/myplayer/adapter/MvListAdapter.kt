package com.zwyl.liyh.myplayer.adapter

import android.content.Context
import com.zwyl.liyh.myplayer.base.BaseListAdapter
import com.zwyl.liyh.myplayer.model.VideosBean
import com.zwyl.liyh.myplayer.widget.MvItemView

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