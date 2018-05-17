package com.zwyl.liyh.myplayer.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.zwyl.liyh.myplayer.R.id.mode
import com.zwyl.liyh.myplayer.model.VBangItemBean
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

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
    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }

    companion object {
        val MODE_ALL_NO_LOOP = 0    //顺序播放
        val MODE_ALL_LOOP = 1       //全部循环
        val MODE_SINGLE = 2         //单曲播放
        val MODE_SINGLE_LOOP = 3    //单曲循环播放
        val MODE_RANDOM = 4         //随机播放
    }

    var mode = MODE_ALL_NO_LOOP

    override fun onCreate() {
        info { "onCreate() is called" }
        mode = sp.getInt("mode", MODE_ALL_LOOP)
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

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        //获取当前播放模式
        override fun getCurMode(): Int = mode

        //设置播放模式
        override fun setPlayMode(currentMode: Int) {
            mode = currentMode
            sp.edit().putInt("mode", mode).commit()
        }


        //播放结束监听
        override fun onCompletion(mp: MediaPlayer?) {
            playNextByMode()
        }

        //根据模式播放下一曲
        private fun playNextByMode() {
            when (mode) {
                MODE_ALL_NO_LOOP -> {
                    list?.let {
                        position = (position + 1) % it.size
                    }
                }
                MODE_ALL_NO_LOOP -> {
                    list?.let {
                        if (position == it.size) return
                        else position++
                    }
                }
                MODE_SINGLE -> {
                    return
                }
                MODE_RANDOM -> {
                    list?.let {
                        val tempPos = position
                        while (tempPos == position) {
                            position = Random().nextInt(it.size)
                        }
                    }
                }
            }
            playItem()
        }

        //设置播放进度
        override fun setProgress(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        //获取总进度
        override fun getDuration(): Int? = mediaPlayer?.duration

        //获取当前进度
        override fun getProgress(): Int? = mediaPlayer?.currentPosition

        /**
         * 通知界面更新
         */
        override fun notfityUpdateUi() {
            EventBus.getDefault().post(list?.get(position))
        }

        override fun isPlaying(): Boolean? = mediaPlayer?.isPlaying
        /**
         * 更新播放状态
         */
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

        fun playItem() {
            mediaPlayer = mediaPlayer ?: MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }
        }

        override fun start() {
            mediaPlayer?.start()
        }

        override fun pause() {
            mediaPlayer?.pause()
        }

        override fun stop() {
            mediaPlayer?.stop()
        }

        override fun prev() {
        }

        override fun next() {
        }

    }
}