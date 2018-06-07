package com.liyh.player.http

import com.liyh.player.model.YueDanBean
import com.liyh.player.util.URLProviderUtils

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/25  Time: 12:12
 * Description:
 */
class YuedanRequest(type: Int, offset: Int, callBack: HttpCallBack<YueDanBean>)
    : MRequest<YueDanBean>(type, URLProviderUtils.getYueDanUrl(offset, 20), callBack)