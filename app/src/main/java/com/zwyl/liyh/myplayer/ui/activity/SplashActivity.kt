package com.zwyl.liyh.myplayer.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 16:06
 * Description:启动页面
 */
class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initView() {
        ViewCompat.animate(iv_aplash).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }
}