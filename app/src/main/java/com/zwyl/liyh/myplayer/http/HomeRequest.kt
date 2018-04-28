package com.zwyl.liyh.myplayer.http

import com.zwyl.liyh.myplayer.model.HomeItemBean
import com.zwyl.liyh.myplayer.util.URLProviderUtils

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