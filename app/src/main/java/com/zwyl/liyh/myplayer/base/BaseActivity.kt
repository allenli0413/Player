package com.zwyl.liyh.myplayer.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 15:16
 * Description:所有activty的基类
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
        initListener()
    }

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化UI
     */
    open protected fun initView() {
    }

    /**
     * 初始化数据
     */
    open protected fun initData() {
    }

    /**
     * adapter,listener相关
     */
    open protected fun initListener() {
    }

    protected fun myToast(msg: String) {
        runOnUiThread {
            toast(msg)
        }
    }

    protected fun myToast(strRes: Int) {
        runOnUiThread {
            toast(strRes)
        }
    }

    protected fun myLongToast(msg: String) {
        runOnUiThread {
            longToast(msg)
        }
    }

    protected fun myLongToast(strRes: Int) {
        runOnUiThread {
            longToast(strRes)
        }
    }

    /**
     * 开启一个activity并且finshdangqianacivity
     */
    inline protected fun <reified T : BaseActivity> startActivityAndFinish() {
        startActivity<T>()
        finish()
    }
}