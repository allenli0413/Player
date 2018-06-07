package com.liyh.player.service

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.liyh.player.R
import com.liyh.player.model.VBangItemBean
import com.liyh.player.ui.activity.AudioPlayerActivity
import com.liyh.player.ui.activity.MainActivity
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
    var position: Int = -2
    val audioBinder by lazy { AudioBinder() }
    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }

    var notificationManager: NotificationManager? = null
    val key_from = "from"
    val FROM_PRE = 1
    val FROM_NEXT = 2
    val FROM_STATE = 3
    val FROM_CONTENT = 4
    var vBangItemBean: VBangItemBean? = null
    var notification: Notification? = null

    companion object {
        val MODE_ALL_NO_LOOP = 0    //顺序播放
        val MODE_ALL_LOOP = 1       //全部循环
        val MODE_SINGLE = 2         //单曲播放
        val MODE_SINGLE_LOOP = 3    //单曲循环播放
        val MODE_RANDOM = 4         //随机播放
    }

    var mode = MODE_ALL_NO_LOOP
    var channelId = "player"

    override fun onCreate() {
        info { "onCreate() is called" }
        mode = sp.getInt("mode", MODE_ALL_LOOP)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channelName = "播放音乐"
            var importance = NotificationManager.IMPORTANCE_HIGH
            createNotificationChannel(channelId, channelName, importance)
        }
        super.onCreate()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String, importance: Int) {
        val channel = NotificationChannel(channelId, channelName, importance)
        channel.enableLights(false); //是否在桌面icon右上角展示小红点
        channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
        channel.enableVibration(false) //是否震动
        channel.setSound(Uri.EMPTY, null)
        notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager?.createNotificationChannel(channel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        info { "onStartCommand() is called" }

        intent?.let {
            val from = it.getIntExtra(key_from, -1)
            when (from) {
                FROM_PRE -> audioBinder.prev()
                FROM_NEXT -> audioBinder.next()
                FROM_STATE -> {
                    audioBinder.updatePlayStatus()
                }
                FROM_CONTENT -> {
                    audioBinder.notfityUpdateUi()
                }
                else -> {
                    list = it.getParcelableArrayListExtra<VBangItemBean>("list")
                    val pos = intent.getIntExtra("position", 0)
                    if (pos == position) {//是同一首歌
                        audioBinder.notfityUpdateUi()
                    } else {
                        position = pos
                        audioBinder.playItem()
                    }
                    vBangItemBean = list?.get(position)
                }
            }

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
        notificationManager?.cancel(1)
        notificationManager = null
        super.onDestroy()
    }

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        //获取当前播放列表集合
        override fun getList(): ArrayList<VBangItemBean>? = list

        //播放传入位置的歌曲
        override fun playPosition(position: Int) {
            this@AudioService.position = position
            playItem()
        }

        //获取当前播放模式
        override fun getCurMode(): Int = mode

        //设置播放模式
        override fun setPlayMode(currentMode: Int) {
            mode = currentMode
            sp.edit().putInt("mode", mode).commit()
        }


        //播放结束监听
        override fun onCompletion(mp: MediaPlayer?) {
            info { "播放结束" }
            playNextByMode()
        }

        //根据模式播放下一曲
        private fun playNextByMode() {
            when (mode) {
                MODE_ALL_LOOP -> {
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
                    notfityUpdateUi()
                    return
                }
                MODE_SINGLE_LOOP -> {
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
            start()
        }

        //获取总进度
        override fun getDuration(): Int? = mediaPlayer?.duration

        //获取当前进度
        override fun getProgress(): Int? = mediaPlayer?.currentPosition

        /**
         * 通知界面更新
         */
        override fun notfityUpdateUi() {
            EventBus.getDefault().post(vBangItemBean)
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
            //通知更新UI
            notfityUpdateUi()

        }

        //显示通知栏的通知
        private fun showNotifaction() {
            notificationManager = notificationManager ?: getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification = getNotification()
            notificationManager?.notify(1, notification)
        }

        /**
         * 获取Notification
         * 3.0以前直接new出来
         * 3.0以后通过build出来
         * 兼容 用NotificationCompat
         */
        private fun getNotification(): Notification? {
            vBangItemBean?.let {
                return NotificationCompat.Builder(applicationContext, channelId)
                        .setTicker("正在播放音乐${it.displayName}")
                        .setSound(Uri.EMPTY)
//                            .setNumber(2)
//                        .setContentTitle(it.displayName)//通知标题
//                        .setContentText(it.artist)//通知内容
//                        .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setWhen(System.currentTimeMillis())
                        .setCustomContentView(getRemoteViews())//设置自定义的通知栏
                        .setOngoing(true) //设置不被滑动删除
                        .setContentIntent(getPendingIntent())//设置通知栏主体点击事件
                        .build()

            }
            return null
        }


        fun playItem() {
            vBangItemBean = list?.get(position)
            releaseMediaPlayer()
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                info { "当前播放的位置：$position" }
                it.setDataSource(vBangItemBean?.data)
                it.prepareAsync()
            }
        }

        override fun start() {
            mediaPlayer?.start()
            notfityUpdateUi()
//            notification?.contentView?.setImageViewResource(R.id.state, R.mipmap.btn_audio_play_normal)
            //显示通知栏
            showNotifaction()

        }

        override fun pause() {
            mediaPlayer?.pause()
            notfityUpdateUi()
//            notification?.contentView?.setImageViewResource(R.id.state, R.mipmap.btn_audio_pause_normal)
            showNotifaction()
        }

        override fun stop() {
            mediaPlayer?.stop()
        }

        override fun prev() {
            list?.let {
                position = if (position == 0) it.size - 1
                else position - 1
                playItem()
            }
        }

        override fun next() {
            list?.let {

                position = (position + 1) % it.size
                playItem()

            }
        }

    }

    //获取自定义通知栏
    private fun getRemoteViews(): RemoteViews? {
        val remoteViews = RemoteViews(packageName, R.layout.notification)
        vBangItemBean?.let {
//            remoteViews.setBoolean(R.id.title, "setSelected", true)
            remoteViews.setTextViewText(R.id.title, it.displayName)
            remoteViews.setTextViewText(R.id.artist, it.artist)
            remoteViews.setOnClickPendingIntent(R.id.pre, getBtnPendingIntent(FROM_PRE))
            remoteViews.setOnClickPendingIntent(R.id.next, getBtnPendingIntent(FROM_NEXT))
            remoteViews.setOnClickPendingIntent(R.id.state, getBtnPendingIntent(FROM_STATE))
            val playing = audioBinder.isPlaying()
            playing?.let { remoteViews.setImageViewResource(R.id.state, if (playing) R.mipmap.btn_audio_play_normal else R.mipmap.btn_audio_pause_normal) }
        }
        return remoteViews
    }

    //各个按钮的点击事件
    private fun getBtnPendingIntent(from: Int): PendingIntent? {
        val intent = Intent(this@AudioService, AudioService::class.java)
        intent.putExtra(key_from, from)
        return PendingIntent.getService(this@AudioService, from, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    //主体点击事件
    private fun getPendingIntent(): PendingIntent? {
        val intentM = Intent(this@AudioService, MainActivity::class.java)
        val intentA = Intent(this@AudioService, AudioPlayerActivity::class.java)
        intentA.putExtra(key_from, FROM_CONTENT)
        val intents = arrayOf(intentM, intentA)
        return PendingIntent.getActivities(this@AudioService, 1, intents, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    //释放MediaPlayer
    private fun releaseMediaPlayer() {
        mediaPlayer?.let {
            it.reset()
            it.release()
        }
        mediaPlayer = null
    }
}