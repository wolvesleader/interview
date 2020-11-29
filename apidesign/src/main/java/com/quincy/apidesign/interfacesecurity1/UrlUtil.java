package com.quincy.apidesign.interfacesecurity1;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: UrlUtil
 *
 * @author: quincy
 * Date: 2020/11/29 下午8:27
 * History:
 */

public class UrlUtil {

    public static class UrlEntity {
        /**
         * 基础url
         */
        public String baseUrl;
        /**
         * url参数
         */
        public Map<String, String> params;

        public String getParam(String key) {
            return params.get(key);
        }
    }

    /**
     * 解析url
     *
     * @param url
     * @return
     */
    public static UrlEntity parse(String url) {
        UrlEntity entity = new UrlEntity();
        if (url == null) {
            return entity;
        }
        url = url.trim();
        if (url.equals("")) {
            return entity;
        }
        String[] urlParts = url.split("\\?");
        entity.baseUrl = urlParts[0];
        //没有参数
        if (urlParts.length == 1) {
            return entity;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        entity.params = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            entity.params.put(keyValue[0], keyValue[1]);
        }

        return entity;
    }
}
