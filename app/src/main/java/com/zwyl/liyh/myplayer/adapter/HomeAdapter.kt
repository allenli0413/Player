package com.zwyl.liyh.myplayer.adapter

import android.content.Context
import com.itheima.player.model.bean.HomeItemBean
import com.zwyl.liyh.myplayer.base.BaseListAdapter
import com.zwyl.liyh.myplayer.widget.HomeItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 22:17
 * Description:首页列表适配器
 */
class HomeAdapter : BaseListAdapter<HomeItemBean, HomeItemView>() {
    override fun refreshItemView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): HomeItemView? = HomeItemView(context)

}