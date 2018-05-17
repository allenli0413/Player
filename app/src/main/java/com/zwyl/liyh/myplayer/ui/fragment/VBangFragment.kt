package com.zwyl.liyh.myplayer.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.AsyncTask
import android.os.Build
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.view.View
import com.zwyl.liyh.myplayer.R
import com.zwyl.liyh.myplayer.adapter.VBangAdapter
import com.zwyl.liyh.myplayer.base.BaseFragment
import com.zwyl.liyh.myplayer.model.AudioBean
import com.zwyl.liyh.myplayer.model.VBangItemBean
import com.zwyl.liyh.myplayer.ui.activity.AudioPlayerActivity
import com.zwyl.liyh.myplayer.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 21:28
 * Description:V榜fragment
 */
class VBangFragment : BaseFragment() {
    var vBangAdapter: VBangAdapter? = null
    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            msg?.let {
                val cursor: Cursor = it.obj as Cursor
                CursorUtil.logCursor(cursor)
            }
        }
    }

    override fun initView(): View? = View.inflate(activity, R.layout.fragment_vbang, null)

    override fun initData() {
        handlePremission()
    }

    /**
     * 处理权限问题
     */
    private fun handlePremission() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val checkSelfPermission = ActivityCompat.checkSelfPermission(activity, permission)
        //查看是否有权限
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //以获取权限
            loadSongs()
        } else {
            //为获取权限， 判断是否需要弹出自己的弹窗
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                //需要弹出
                alert("我们只会访问音乐文件，不会访问隐私数据", "温馨提示") {
                    yesButton { requestMyPermission() }
                    noButton { }
                }.show()
            } else {
                //不需要弹出
                requestMyPermission()
            }
        }
    }

    /**
     * 真正的申请权限
     */
    private fun requestMyPermission() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permissions, 1)
    }

    /**
     * 处理权限请求结果
     * @param requestCode 请求码
     * @param permissions 申请权限数组
     * @param grantResults 申请结果数组
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSongs()
        }
    }

    /**
     * 加载本地音乐列表
     */
    private fun loadSongs() {
        //添加音乐列表
        val resolver = context.contentResolver
        //方法一
        //        cursor.close()
        //        Thread(object : Runnable {
        //            override fun run() {
        //                val cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        //                        arrayOf(MediaStore.Audio.Media.DATA,
        //                                MediaStore.Audio.Media.DISPLAY_NAME,
        //                                MediaStore.Audio.Media.SIZE,
        //                                MediaStore.Audio.Media.ARTIST),
        //                        null, null, null)
        //                val msg = Message.obtain()
        //                msg.obj = cursor
        //                handler.sendMessage(msg)
        //            }
        //        }).start()

        //方法二
        //        AudioTask().execute(resolver)
        //方法三
        val aqHandler = @SuppressLint("HandlerLeak")
        object : AsyncQueryHandler(resolver) {
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                CursorUtil.logCursor(cursor)
                //设置数据源，并刷新
                (cookie as VBangAdapter).swapCursor(cursor)
            }
        }
        aqHandler.startQuery(0, vBangAdapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.ARTIST),
                null, null, null)
    }

    override fun initListener() {
        vBangAdapter = VBangAdapter(context, null)
        lv_fragment_vbang.adapter = vBangAdapter
        lv_fragment_vbang.setOnItemClickListener { parent, view, position, id ->
            //获取条目对应的cursor
            val cursor = vBangAdapter?.getItem(position) as Cursor
            //通过cursor获取整个集合
            val audioList : ArrayList<VBangItemBean> = VBangItemBean.getAudioBeans(cursor)
            startActivity<AudioPlayerActivity>("list" to audioList, "position" to position)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onDestroy() {
        super.onDestroy()
        //界面销毁
        //关闭adapter里的cursor
        // 将cursor设为null
        vBangAdapter?.changeCursor(null)
    }

    /**
     * 音乐查询异步任务
     */
    class AudioTask : AsyncTask<ContentResolver, Void, Cursor>() {
        /**
         * 后台执行任务 新线程
         */
        override fun doInBackground(vararg params: ContentResolver?): Cursor? {
            val cursor = params[0]?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(MediaStore.Audio.Media._ID,
                            MediaStore.Audio.Media.DATA,
                            MediaStore.Audio.Media.DISPLAY_NAME,
                            MediaStore.Audio.Media.SIZE,
                            MediaStore.Audio.Media.ARTIST),
                    null, null, null)
            return cursor
        }

        /**
         * 主线程执行
         */
        override fun onPostExecute(result: Cursor?) {
            CursorUtil.logCursor(result)

        }
    }
}