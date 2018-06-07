package com.liyh.player.http

import com.liyh.player.model.HomeItemBean
import com.liyh.player.util.URLProviderUtils

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/24  Time: 13:38
 * Description:首页的request
 */
class HomeRequest(type: Int, offset: Int, httpCallBack: HttpCallBack<List<HomeItemBean>>)
    : MRequest<List<HomeItemBean>>(type, URLProviderUtils.getHomeUrl(offset, 20), httpCallBack) {
}