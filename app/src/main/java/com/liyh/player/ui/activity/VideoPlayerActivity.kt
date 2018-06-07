package com.liyh.player.ui.activity

import com.liyh.player.R
import com.liyh.player.base.BaseActivity
import com.liyh.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/28  Time: 14:02
 * Description:Mv播放页面
 */
class VideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_video_player

    override fun initView() {
        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        vv_activity_video_player.setVideoPath(videoPlayBean.url)
        vv_activity_video_player.showContextMenu()
        vv_activity_video_player.setOnPreparedListener {
            vv_activity_video_player.start()

        }
    }
}