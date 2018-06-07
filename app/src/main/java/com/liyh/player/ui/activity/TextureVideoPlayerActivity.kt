package com.liyh.player.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.liyh.player.R
import com.liyh.player.base.BaseActivity
import com.liyh.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_textutre.*
import org.jetbrains.anko.info

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/28  Time: 14:02
 * Description:Mv播放页面
 */
class TextureVideoPlayerActivity : BaseActivity(), TextureView.SurfaceTextureListener {

    var videoPlayBean: VideoPlayBean? = null
    val mediaPlayer by lazy { MediaPlayer() }
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        //view大小发生改变
        info { "width: $width, height: $height" }
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        //视图更新
        info { "视图更新中。。。" }
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        //视图销毁
        mediaPlayer.run {
            stop()
            release()
        }

        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        //视图可用
        videoPlayBean?.let {
            mediaPlayer.setDataSource(it.url)
            mediaPlayer.setSurface(Surface(surface))//设置播放视频画面
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
                texture_view.rotation = 90f
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_video_player_textutre

    override fun initView() {
        videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        texture_view.surfaceTextureListener = this
    }
}