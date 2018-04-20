package com.zwyl.liyh.myplayer.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zwyl.liyh.myplayer.widget.HomeItemView

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/20  Time: 22:17
 * Description:
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent?.context))
    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {

    }

    class HomeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}