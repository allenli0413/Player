package com.zwyl.liyh.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.model.HomeItemBean
import kotlinx.android.synthetic.main.home_item_view.view.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:53
 * Description:首页itemview
 */
class HomeItemView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.home_item_view, this)
    }

    /**
     * 设置数据
     */
    fun setData(data: HomeItemBean) {
        Picasso.get().load(data.posterPic).placeholder(R.mipmap.music_bg).into(iv_home_item_bg)
        tv_home_item_title.text = data.title
        tv_home_item_desc.text = data.description
        when (data.type) {
            "PLAYLIST" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_playlist)
            "PROGRAM" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_program)
            "ACTIVITY" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_activity)
            "AD" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_ad)
            "BULLETIN" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_bulletin)
            "FANART" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_fanart)
            "LIVE" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_live)
            "LIVENEW" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_live_new)
            "PROJECT" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_project)
            "STAR" -> iv_home_item_icon.setImageResource(R.mipmap.home_page_star)
            else -> iv_home_item_icon.setImageResource(R.mipmap.home_page_video)
        }
    }
}
