package com.liyh.player.http

import com.liyh.player.model.MvPagerBean
import com.liyh.player.util.URLProviderUtils

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