package com.liyh.player.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.liyh.player.R
import com.liyh.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 16:06
 * Description:启动页面
 */
class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    //动画结束
    override fun onAnimationEnd(view: View?) {
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {}

    override fun onAnimationStart(view: View?) {}

    override fun getLayoutId(): Int = R.layout.activity_splash


    override fun initView() {
//        动画开始
        ViewCompat.animate(iv_aplash).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2001
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}