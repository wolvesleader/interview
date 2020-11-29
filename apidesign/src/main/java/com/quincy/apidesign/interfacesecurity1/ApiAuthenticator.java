package com.quincy.apidesign.interfacesecurity1;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: ApiAuthenticator
 *
 * @author: quincy
 * Date: 2020/11/29 下午6:18
 * History:
 * 用来给外部提供鉴权接口
 */

public interface ApiAuthenticator {
    void auth(String url);
    void auth(ApiRequest apiRequest);
}
