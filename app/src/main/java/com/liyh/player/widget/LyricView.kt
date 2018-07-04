package com.liyh.player.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.liyh.player.R
import com.liyh.player.model.LyricBean
import com.liyh.player.util.LyricLoader
import com.liyh.player.util.LyricUtil
import org.jetbrains.anko.doAsync

/**
 * @author  Yahri Lee
 * @date  2018 年 06 月 07 日
 * @time  15 时 54 分
 * @descrip :
 */
class LyricView : View {
    val lrcList by lazy { ArrayList<LyricBean>() }
    val paint by lazy { Paint(ANTI_ALIAS_FLAG) }
    var viewH = 0
    var viewW = 0
    var bigSize = 0f
    var smallSize = 0f
    var white = 0
    var green = 0
    var centerLine = 0
    var lineHeight = 0
    var duration = 0
    private var progress = 0
    var offsetHeight = 0F


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bigSize = resources.getDimension(R.dimen.bigSize)
        smallSize = resources.getDimension(R.dimen.smallSize)
        white = ContextCompat.getColor(context, R.color.white)
        green = ContextCompat.getColor(context, R.color.green)
        paint.textAlign = Paint.Align.CENTER
        lineHeight = resources.getDimensionPixelOffset(R.dimen.lineHeight)
//        for (i in 0 until 30) {
//            lrcList.add(LyricBean(2000 * i, "正在播放第${i}行歌词"))
//        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (lrcList.isEmpty()) {
            drawSingleLine(canvas)
        } else {
            drawMultiLine(canvas)
        }
    }

    /**
     * 绘制多行居中文本
     */
    private fun drawMultiLine(canvas: Canvas?) {
        if (updateByPro) {
            val curStartTime = lrcList.get(centerLine).startTime
            //行可用时间
            var lineTime = 0
            //最后一行居中
            if (centerLine == lrcList.size - 1) {
                lineTime = duration - curStartTime
            } else {
                //其他行居中
                val nextStartTime = lrcList[centerLine + 1].startTime
                lineTime = nextStartTime - curStartTime
            }
            //行偏移时间
            val offsetTime = progress - curStartTime
            //偏移百分比
            val offsetPresent = offsetTime / lineTime.toFloat()
            //行偏移高度
            offsetHeight = offsetPresent * lineHeight
        }

        val text = lrcList[centerLine].content
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        val textH = bounds.height()
        //居中行
        val centerY = (viewH + textH) / 2 - offsetHeight
        val x = viewW / 2
        for ((index, lyricBean) in lrcList.withIndex()) {
            if (index == centerLine) {
                paint.color = green
                paint.textSize = bigSize
            } else {
                paint.color = white
                paint.textSize = smallSize
            }
            val curY = centerY + (index - centerLine) * lineHeight
            //超出边界处理
            if (curY < 0) {
                continue
            }
            if (curY > viewH + lineHeight) {
                break
            }
            val curText = lyricBean.content
            canvas?.drawText(curText, x.toFloat(), curY, paint)
        }
    }

    /**
     * 绘制当行居中文本
     */
    private fun drawSingleLine(canvas: Canvas?) {
        paint.color = green
        paint.textSize = bigSize
        val text = "正在加载歌词..."
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        val textH = bounds.height()
        val textW = bounds.width()
        //        val x = (viewW - textW) / 2
        val x = viewW / 2
        val y = (viewH + textH) / 2
        canvas?.drawText(text, x.toFloat(), y.toFloat(), paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewH = h
        viewW = w
    }

    fun updateProgress(progress: Int) {
        if (lrcList.isEmpty()) return
        if (updateByPro) {
            this.progress = progress
            //获取居中行，首先判断居中行是否是最后一行
            if (progress > lrcList[lrcList.size - 1].startTime) {
                //最后一行居中
                centerLine = lrcList.size - 1
            } else {
                //其它行，循环遍历
                for (line in 0 until lrcList.size - 1) {
                    val curStartTime = lrcList[line].startTime
                    val netStartTime = lrcList[line + 1].startTime
                    if (progress in curStartTime..(netStartTime - 1)) {
                        centerLine = line
                        break
                    }
                }
            }
            invalidate()
        }
//        invalidate()  会调用onDraw（）方法 view的宽度和高度改变的不能刷新
//        postInvalidate（）会调用onDraw（）方法 同invalidate（）但在子线程执行
//        requestLayout（） view布局参数改变时的刷新方式  宽高改变时需要调用此方法
    }

    fun setSongName(songName: String) {
        doAsync {
            lrcList.clear()
            val loadLyricFile = LyricLoader.loadLyricFile(songName)
            Log.i("path = ", loadLyricFile.absolutePath)
            lrcList.addAll(LyricUtil.parseLrc(loadLyricFile))
        }
    }

    var updateByPro = true //是否可以更新歌词进度
    var downY = 0F
    var markY = 0F
    /**
     * 歌词控制手势事件
     * 1。手指按下，停止更新歌词进度
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //按下停止更新
                updateByPro = false
                downY = event.y
                markY = offsetHeight
            }
            MotionEvent.ACTION_MOVE -> {
                val endY = event.y
                //手指移动的距离
                val offY = downY - endY
                //确定偏移量
                offsetHeight = offY + markY
                //重新确定居中行
                if (Math.abs(offsetHeight) >= lineHeight) {
                    val offsetLine = (offsetHeight / lineHeight).toInt()
                    centerLine += offsetLine
                    //再次设置居中行边界
                    if (centerLine <= 0) centerLine = 0 else if (centerLine >= lrcList.size - 1) centerLine = lrcList.size - 1
                    //重新设置downY
                    downY = endY
                    //重新确定偏移量
                    offsetHeight = offsetHeight % lineHeight
                    //重新确定mark值
                    markY = offsetHeight
                    listener?.invoke(lrcList[centerLine].startTime)
                }
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                updateByPro = true
            }
        }
        return true
    }


    private var listener: ((progress: Int) -> Unit)? = null

    fun setProgressListene(listener: ((progress: Int) -> Unit)?){
        this.listener = listener
    }
}