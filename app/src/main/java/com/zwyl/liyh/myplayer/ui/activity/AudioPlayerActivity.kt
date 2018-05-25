package com.zwyl.liyh.myplayer.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.SeekBar
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.model.VBangItemBean
import com.zwyl.liyh.myplayer.service.AudioService
import com.zwyl.liyh.myplayer.service.IService
import com.zwyl.liyh.myplayer.util.StringUtil
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.info

/**
 * @author  Yahri Lee
 * @date  2018年05月10日
 * @time  14时${分}
 * @descrip
 */
class AudioPlayerActivity : BaseActivity(), View.OnClickListener, Handler.Callback, SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        //进度改变
        if (fromUser) {
            iService?.setProgress(progress)
            updateProgressUi(progress % (duration + 1))
            updatePlayStatusUi()
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        //开始拖动
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        //停止拖动
    }

    //消息处理
    override fun handleMessage(msg: Message?): Boolean {
        when (msg?.what) {
            PROGRESS_MSG -> startUpdateProgress()
        }
        return false
    }

    var iService: IService? = null
    val audioConnection by lazy { AudioConnection() }
    var vBangItemBean: VBangItemBean? = null
    var drawable: AnimationDrawable? = null
    var duration: Int = 0
    val PROGRESS_MSG = 0
    val handle = Handler(this)

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.state -> {
                iService?.updatePlayStatus()
                updatePlayStatusUi()
            }
            R.id.back -> finish()
            R.id.mode -> {
                updateMode()
            }
            R.id.pre -> playPre()
            R.id.next -> playNext()
            R.id.playlist -> finish()
        }
    }

    /**
     * 播放下一首
     */
    private fun playNext() {
        iService?.next()
    }

    /**
     * 播放上一首
     */
    private fun playPre() {
        iService?.prev()
    }

    /**
     * 更新播放模式
     */
    private fun updateMode() {
        var curMode = iService?.getCurMode()
        curMode?.let {
            val currentMode = (curMode + 1) % 5
            iService?.setPlayMode(currentMode)
            updateModeUi()
        }
    }

    /**
     * 根据模式不同更新模式图标
     */
    private fun updateModeUi() {
        val curMode = iService?.getCurMode()
        when (curMode) {
            AudioService.MODE_ALL_LOOP -> mode.setImageResource(R.drawable.selector_btn_playmode_order)
            AudioService.MODE_ALL_NO_LOOP -> mode.setImageResource(R.mipmap.btn_playmode_all_repeat_pressed)
            AudioService.MODE_SINGLE -> mode.setImageResource(R.mipmap.btn_playmode_singlerepeat_pressed)
            AudioService.MODE_SINGLE_LOOP -> mode.setImageResource(R.drawable.selector_btn_playmode_single)
            AudioService.MODE_RANDOM -> mode.setImageResource(R.drawable.selector_btn_playmode_random)
        }
    }

    /**
     * 更新状态UI
     */
    private fun updatePlayStatusUi() {
        val isPlay = iService?.isPlaying()
        info { "isPlay = $isPlay, iService = $iService" }
        isPlay?.let {
            if (it) {
                state.setImageResource(R.drawable.selector_btn_audio_play)
                drawable?.start()
                handle.sendEmptyMessage(PROGRESS_MSG)
            } else {
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                handle.removeMessages(PROGRESS_MSG)
            }
        }
    }

    //开始播放
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    fun startPlayAndUpdateUi(itemBean: VBangItemBean?) {
        itemBean?.let {
            vBangItemBean = itemBean
            audio_title.text = itemBean.displayName
            artist.text = itemBean.artist
            //更新播放按钮的icon
            updatePlayStatusUi()
            //获取动画的drawable
            drawable = audio_anim.background as AnimationDrawable
            //开始动画
            val isPlay = iService?.isPlaying()
            isPlay?.let {
                if (it) {
                    drawable?.start()
                    startUpdateProgress()
                } else drawable?.stop()

            }
            //获取总时长
            duration = iService?.getDuration() ?: 0
            //给进度条设置最大值
            progress_sk.max = duration
            //更新进度条
            updateModeUi()
        }
    }

    /**
     * 开始更新播放进度
     */
    private fun startUpdateProgress() {
        //获取播放进度
        val progress: Int = iService?.getProgress() ?: 0
        //更新进度
        updateProgressUi(progress)
        if (progress < duration)
            handle.sendEmptyMessageDelayed(PROGRESS_MSG, 1000)
        else handle.removeMessages(PROGRESS_MSG)
    }

    //更新新进度ui
    private fun updateProgressUi(mPro: Int) {
        info { "mPro = $mPro, duration = $duration" }
        val dur = StringUtil.handleTime(duration)
        val pro = StringUtil.handleTime(mPro)
        info { "pro = $pro, dur = $dur" }

        progress.text = "$pro/$dur"
        progress_sk.progress = mPro
    }

    override fun getLayoutId(): Int = R.layout.activity_audio_palyer

    override fun initData() {
        EventBus.getDefault().register(this)
//        info { "list = ${list.toString()}, position = $position" }
        val serviceIntent = intent.setClass(this, AudioService::class.java)
        //先绑定服务
        bindService(serviceIntent, audioConnection, Context.BIND_AUTO_CREATE)
        //再开启服务
        startService(serviceIntent)
    }

    override fun initListener() {
        state.setOnClickListener(this)
        back.setOnClickListener(this)
        mode.setOnClickListener(this)
        pre.setOnClickListener(this)
        next.setOnClickListener(this)
        playlist.setOnClickListener(this)
        progress_sk.setOnSeekBarChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(audioConnection)
        //反注册eventbus
        EventBus.getDefault().unregister(this)
        //移除所有消息和回调，防止内存泄漏
        handle.removeCallbacksAndMessages(null)
    }

    inner class AudioConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            //服务意外断开，不是主动断开
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //服务连接
            iService = service as IService
//            iService?.playItem()
        }

    }
}