package com.zwyl.liyh.myplayer.http

import com.zwyl.liyh.myplayer.model.MvAreaBean
import com.zwyl.liyh.myplayer.util.URLProviderUtils

/**
 * Author: liyh
 * E-mail: liyh@zhiwyl.com
 * Phone: 15033990609
 * Date: 2018/4/27  Time: 18:04
 * Description:
 */
class MvAreaRequest(callBack: HttpCallBack<List<MvAreaBean>>) : MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), callBack) {

}