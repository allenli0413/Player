package com.zwyl.liyh.myplayer.ui.activity

import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*
import org.jetbrains.anko.info

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/28  Time: 14:02
 * Description:Mv播放页面
 */
class IjkVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_video_player_ijk

    override fun initView() {
        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        ijk_video_view.setVideoPath(videoPlayBean.url)
        ijk_video_view.showContextMenu()
        ijk_video_view.setOnPreparedListener {
            info { "开始播放" }
            ijk_video_view.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ijk_video_view.stopPlayback()
    }
}