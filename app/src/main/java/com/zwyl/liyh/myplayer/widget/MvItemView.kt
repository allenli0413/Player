package com.zwyl.liyh.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.model.VideosBean
import kotlinx.android.synthetic.main.mv_item_view.view.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 21:44
 * Description:
 */
class MvItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.mv_item_view, this)
    }

    fun setData(data: VideosBean){
        tv_mv_item_song.text = data.title
        tv_mv_item_songer.text = data.artistName
        Picasso.get().load(data.playListPic).placeholder(R.mipmap.music_bg).into(iv_mv_item_bg)
    }
}