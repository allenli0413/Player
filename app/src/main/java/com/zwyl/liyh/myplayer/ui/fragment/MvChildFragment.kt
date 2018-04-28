package com.zwyl.liyh.myplayer.ui.fragment

import com.zwyl.liyh.myplayer.adapter.MvListAdapter
import com.zwyl.liyh.myplayer.base.BaseListAdapter
import com.zwyl.liyh.myplayer.base.BaseListFragment
import com.zwyl.liyh.myplayer.base.BaseListPresenter
import com.zwyl.liyh.myplayer.model.MvPagerBean
import com.zwyl.liyh.myplayer.model.VideoPlayBean
import com.zwyl.liyh.myplayer.model.VideosBean
import com.zwyl.liyh.myplayer.presenter.impl.MvListPresenterImpl
import com.zwyl.liyh.myplayer.ui.activity.JiaoZiVideoPlayerActivity
import com.zwyl.liyh.myplayer.widget.MvItemView
import org.jetbrains.anko.support.v4.startActivity

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 18:41
 * Description:
 */
class MvChildFragment : BaseListFragment<MvPagerBean, VideosBean, MvItemView>() {

    var code: String = ""
    override fun init() {
        code = arguments.getString("args")
    }

    override fun getList(result: MvPagerBean?): List<VideosBean>? = result?.videos

    override fun getSelfAdapter(): BaseListAdapter<VideosBean, MvItemView> = MvListAdapter()

    override fun getSelfPresenter(): BaseListPresenter = MvListPresenterImpl(code, this)

    override fun initListener() {
        super.initListener()
        mAdapter.setOnMyItemClickListener {
            val videoPlayBean = VideoPlayBean(it.id, it.title, it.url)
            startActivity<JiaoZiVideoPlayerActivity>("item" to videoPlayBean)
        }
    }
}