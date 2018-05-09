package com.zwyl.liyh.myplayer.adapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.View
import android.view.ViewGroup
import com.zwyl.liyh.myplayer.model.VBangItemBean
import com.zwyl.liyh.myplayer.widget.VBandItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/5/9  Time: 12:08
 * Description:
 */
class VBangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c, false) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View = VBandItemView(context)

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val vBandItemBean = VBangItemBean.getVBandItemBean(cursor)
        (view as VBandItemView).setData(vBandItemBean)
    }

}
