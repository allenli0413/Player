package com.zwyl.liyh.myplayer.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.model.VBangItemBean
import kotlinx.android.synthetic.main.vbang_item_view.view.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/5/9  Time: 12:12
 * Description:
 */
class VBandItemView:RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.vbang_item_view, this)
    }

    fun setData(itemBean: VBangItemBean) {
        tv_vbang_title.text = itemBean.displayName
        tv_vbang_artist.text = itemBean.artist
        val size = Formatter.formatFileSize(context, itemBean.size)
        tv_vbang_size.text = size
    }
}