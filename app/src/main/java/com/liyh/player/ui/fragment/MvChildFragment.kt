package com.liyh.player.ui.fragment

import com.liyh.player.adapter.MvListAdapter
import com.liyh.player.base.BaseListAdapter
import com.liyh.player.base.BaseListFragment
import com.liyh.player.base.BaseListPresenter
import com.liyh.player.model.MvPagerBean
import com.liyh.player.model.VideoPlayBean
import com.liyh.player.model.VideosBean
import com.liyh.player.presenter.impl.MvListPresenterImpl
import com.liyh.player.ui.activity.JiaoZiVideoPlayerActivity
import com.liyh.player.widget.MvItemView
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
        code = arguments!!.getString("args")
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