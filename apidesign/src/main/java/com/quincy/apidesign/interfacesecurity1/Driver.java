package com.quincy.apidesign.interfacesecurity1;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Driver
 *
 * @author: quincy
 * Date: 2020/11/29 下午7:55
 * History:
 */

public class Driver {
    public static void main(String[] args) {
        ApiAuthenticator apiAuthenticator = new DefaultApiAuthenticatorImpl();
        String baseUrl = "www.iexchangebook.com";
        String appId = "ys1";
        String password = "DAJIAHAO";
        long timestamp = System.currentTimeMillis();
       // String token = AuthToken.genToken(baseUrl, appId, password, timestamp);
       // String finalUrl = baseUrl + "?appId=" + appId + "&token=" + token + "&timestamp=" + timestamp + 100000L;
        //System.out.println(finalUrl);
       // apiAuthenticator.auth(finalUrl);
    }
}
