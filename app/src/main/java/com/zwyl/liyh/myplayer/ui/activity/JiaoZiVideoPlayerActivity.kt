package com.zwyl.liyh.myplayer.ui.activity

import android.support.v4.view.ViewPager
import android.widget.RadioButton
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.adapter.MvPlayerViewPageAdapter
import com.zwyl.liyh.myplayer.base.BaseActivity
import com.zwyl.liyh.myplayer.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_jiaozi.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/28  Time: 14:02
 * Description:Mv播放页面
 */
class JiaoZiVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_video_player_jiaozi

    override fun initView() {
        val data = intent.data
        if (data == null) {
            //本应用内视频请求
            val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
            jz_video_player.setUp(videoPlayBean.url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoPlayBean.title)
        } else {
            val sData = data.toString()
            if (sData.startsWith("http")) {
                //应用外网络视频请求
                jz_video_player.setUp(sData, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, sData)
            } else {
                //应用外本地视频请求
                jz_video_player.setUp(data.path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, data.path)
            }
        }
    }

    override fun onBackPressed() {
        if (JZVideoPlayer.backPress()) return
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayerStandard.releaseAllVideos()
    }

    override fun initListener() {
        super.initListener()
        mv_viewpager.adapter = MvPlayerViewPageAdapter(supportFragmentManager)
        rg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb1 -> mv_viewpager.currentItem = 0
                R.id.rb2 -> mv_viewpager.currentItem = 1
                R.id.rb3 -> mv_viewpager.currentItem = 2
            }
        }

        mv_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                //滑动状态改变
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //页面滚动时调用
            }

            override fun onPageSelected(position: Int) {
                //页面被选中
                val childView = rg.getChildAt(position) as RadioButton
                childView.isChecked = true
            }
        })
    }
}