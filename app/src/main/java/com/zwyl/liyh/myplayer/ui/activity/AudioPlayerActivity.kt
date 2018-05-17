package com.zwyl.liyh.myplayer.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.support.annotation.MainThread
import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.R.id.state
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.model.VBangItemBean
import com.zwyl.liyh.myplayer.service.AudioService
import com.zwyl.liyh.myplayer.service.IService
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
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
class AudioPlayerActivity : BaseActivity(), View.OnClickListener {
    var iService: IService? = null
    val audioConnection by lazy { AudioConnection() }
    var vBangItemBean: VBangItemBean? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.state -> {
                iService?.updatePlayStatus()
                updatePlayStatusUi()
            }
        }
    }

    /**
     * 更新状态UI
     */
    private fun updatePlayStatusUi() {
        val isPlay = iService?.isPlaying()
        info { "isPlay = $isPlay, iService = $iService" }
        isPlay?.let {
            if (it) state.setImageResource(R.drawable.selector_btn_audio_play)
            else state.setImageResource(R.drawable.selector_btn_audio_pause)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    fun startPlayAndUpdateUi(itemBean: VBangItemBean?) {
        itemBean?.let {
            vBangItemBean = itemBean
            audio_title.text = itemBean.displayName
            artist.text = itemBean.artist
            updatePlayStatusUi()
        }
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
    }

    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(audioConnection)
        EventBus.getDefault().unregister(this)
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