package com.liyh.player.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.liyh.player.R
import org.jetbrains.anko.find

/**
 * @author  Yahri Lee
 * @date  2018 年 05 月 28 日
 * @time  10 时 29 分
 * @descrip :
 */
class AudioListPopWindow(context: Context?, adapter: BaseAdapter, listener: AdapterView.OnItemClickListener,val window: Window): PopupWindow(context) {

    var alpha = 0f
    init {
        alpha = window.attributes.alpha
        val popView = View.inflate(context, R.layout.layout_audio_list_popwindow, null)
        val listView = popView.find<ListView>(R.id.lv_audio_list)
        listView.adapter = adapter
        listView.onItemClickListener = listener
        contentView = popView
//        如果不设置宽高  popupWindow不会显示
        //设置宽度
        width = ViewGroup.LayoutParams.MATCH_PARENT
        //获取windowManager来获取屏幕的宽高
        val manager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //point 用来记录屏幕宽高
        val point = Point()
        manager.defaultDisplay.getSize(point)
        //设置高度
        height = point.y * 3 / 5
        //设置获取焦点
        isFocusable = true
        //设置外部点击
        isOutsideTouchable = true
        //设置响应返回按钮（低版本需要设置）
        setBackgroundDrawable(ColorDrawable())
        //设置进出动画样式
        animationStyle = R.style.pop_style

    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int) {
        super.showAsDropDown(anchor, xoff, yoff)
        val attributes = window.attributes
        attributes.alpha = 0.5f
        window.attributes = attributes
    }

    override fun dismiss() {
        super.dismiss()
        val attributes = window.attributes
        attributes.alpha = alpha
        window.attributes = attributes
    }
}