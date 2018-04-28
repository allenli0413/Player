package com.zwyl.liyh.myplayer.ui.activity

import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.model.VideoPlayBean
import io.vov.vitamio.LibsChecker
import kotlinx.android.synthetic.main.activity_video_player_vitamio.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/28  Time: 14:02
 * Description:Mv播放页面
 */
class VitamioVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_video_player_vitamio

    override fun initView() {
        //初始化Vitamio
        LibsChecker.checkVitamioLibs(this)
        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        vitamio_activity_video_player.setVideoPath(videoPlayBean.url)
        vitamio_activity_video_player.setOnPreparedListener {
            vitamio_activity_video_player.start()
        }
    }
}