package com.zwyl.liyh.myplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.zwyl.liyh.myplayer.model.VBangItemBean
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 16 日
 * @time  18 时 56 分
 * @descrip :音乐播放的服务
 */
class AudioService : Service(), AnkoLogger {
    var mediaPlayer: MediaPlayer? = null
    var list: ArrayList<VBangItemBean>? = null
    var position: Int = 0
    val audioBinder by lazy { AudioBinder() }
    override fun onCreate() {
        info { "onCreate() is called" }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        info { "onStartCommand() is called" }
        intent?.let {
            list = intent.getParcelableArrayListExtra<VBangItemBean>("list")
            position = intent.getIntExtra("position", 0)
            audioBinder.playItem()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        info { "onBind() is called" }

        return audioBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        info { "onUnbind() is called" }

        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        info { "onDestroy() is called" }

        super.onDestroy()
    }

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener {
        /**
         * 通知界面更新
         */
        override fun notfityUpdateUi() {
            EventBus.getDefault().post(list?.get(position))
        }

        override fun isPlaying(): Boolean? = mediaPlayer?.isPlaying

        override fun updatePlayStatus(): Boolean? {
            val isPlaying = isPlaying()
            isPlaying?.let {
                if (isPlaying) pause() else start()
            }
            return isPlaying
        }

        override fun onPrepared(mp: MediaPlayer?) {
            start()
            notfityUpdateUi()
        }

        override fun playItem() {
            mediaPlayer = mediaPlayer ?: MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }
        }

        override fun start() { mediaPlayer?.start() }

        override fun pause() { mediaPlayer?.pause()}

        override fun stop() { mediaPlayer?.stop()}

        override fun prev() {
        }

        override fun next() {
        }

    }
}