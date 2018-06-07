package com.liyh.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.liyh.player.R
import com.liyh.player.model.VBangItemBean
import kotlinx.android.synthetic.main.layout_item_popwindow.view.*

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 28 日
 * @time  11 时 42 分
 * @descrip :
 */
class PopWindowItemView : RelativeLayout{
    fun setData(itemModel: VBangItemBean) {
        tv_pop_title.text = itemModel.displayName
        tv_pop_artist.text = itemModel.artist
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.layout_item_popwindow, this)

    }
}