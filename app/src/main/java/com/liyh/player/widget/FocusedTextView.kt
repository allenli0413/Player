package com.liyh.player.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 29 日
 * @time  17 时 28 分
 * @descrip :
 */
class FocusedTextView : TextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setSingleLine()
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(true)
    }

    override fun setEllipsize(where: TextUtils.TruncateAt?) {
        super.setEllipsize(TextUtils.TruncateAt.MARQUEE)
    }

    override fun setMarqueeRepeatLimit(marqueeLimit: Int) {
        super.setMarqueeRepeatLimit(-1)
    }
}