package com.liyh.player.http

import com.liyh.player.model.MvAreaBean
import com.liyh.player.util.URLProviderUtils

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 18:04
 * Description:
 */
class MvAreaRequest(callBack: HttpCallBack<List<MvAreaBean>>) : MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), callBack) {

}