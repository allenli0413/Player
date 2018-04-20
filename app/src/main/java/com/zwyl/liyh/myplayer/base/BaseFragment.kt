package com.zwyl.liyh.myplayer.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 15:16
 * Description:所有fragment的基类
 */
abstract class BaseFragment : Fragment(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    open protected fun init() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    /**
     * 获取布局view
     */
    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    /**
     * 初始化数据
     */
    open protected fun initData() {

    }

    /**
     * adapter,listener
     */
    open protected fun initListener() {
    }

    protected fun myToast(msg: String) {
        context?.runOnUiThread {
            toast(msg)
        }
    }

    protected fun myToast(strRes: Int) {
        context?.runOnUiThread {
            toast(strRes)
        }
    }

    protected fun myLongToast(msg: String) {
        context?.runOnUiThread {
            longToast(msg)
        }
    }

    protected fun myLongToast(strRes: Int) {
        context?.runOnUiThread {
            longToast(strRes)
        }
    }
}