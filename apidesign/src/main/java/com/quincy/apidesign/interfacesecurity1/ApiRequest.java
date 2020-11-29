package com.quincy.apidesign.interfacesecurity1;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: ApiRequest
 *
 * @author: quincy
 * Date: 2020/11/29 下午5:16
 * History:
 */

public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }


    public static ApiRequest createFromFullUrl(String url){
        //从原始的url中解析出来需要的参数
        //目前中支持get方式请求
        //如果需要支持post，需要编写post请求解析器
        UrlUtil.UrlEntity parse = UrlUtil.parse(url);
        String baseUrl = parse.baseUrl;
        String token = parse.getParam("token");
        String appId = parse.getParam("appId");
        String timestamp = parse.getParam("timestamp");
        return new ApiRequest(baseUrl,token,appId,Long.valueOf(timestamp));
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
