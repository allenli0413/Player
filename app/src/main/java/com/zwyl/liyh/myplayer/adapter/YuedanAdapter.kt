package com.zwyl.liyh.myplayer.adapter

import YueDanBean
import android.content.Context
import com.zwyl.liyh.myplayer.base.BaseListAdapter
import com.zwyl.liyh.myplayer.widget.YueDanItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 22:17
 * Description:悦单页面条目适配器
 */
class YuedanAdapter : BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun getItemView(context: Context?): YueDanItemView? = YueDanItemView(context)

    override fun refreshItemView(itemView: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemView.setData(data)
    }
}