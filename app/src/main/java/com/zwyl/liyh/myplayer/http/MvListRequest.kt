package com.zwyl.liyh.myplayer.http

import com.zwyl.liyh.myplayer.model.MvPagerBean
import com.zwyl.liyh.myplayer.util.URLProviderUtils

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 22:12
 * Description:
 */
class MvListRequest(type: Int, code: String, offset: Int, callBack: HttpCallBack<MvPagerBean>)
    : MRequest<MvPagerBean>(type, URLProviderUtils.getMVListUrl(code, offset, 20), callBack) {
}