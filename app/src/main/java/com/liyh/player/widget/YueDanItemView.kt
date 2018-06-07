package com.liyh.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.liyh.player.R
import com.liyh.player.model.YueDanBean
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.yuedan_item_view.view.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/24  Time: 20:29
 * Description:
 */
class YueDanItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.yuedan_item_view, this)
    }

    fun setData(data: YueDanBean.PlayListsBean) {
        Picasso.get().load(data.playListBigPic).placeholder(R.mipmap.music_bg).into(iv_yuedan_item_bg)
        Picasso.get().load("http:${data.creator?.largeAvatar}").placeholder(R.mipmap.music_default_bg).transform(CropCircleTransformation()).into(iv_home_item_avatar)
        tv_yuedan_item_title.text = data.title
        tv_yuedan_item_author.text = data.creator?.nickName
        tv_yuedan_item_count.text = data.videoCount.toString()
    }
}