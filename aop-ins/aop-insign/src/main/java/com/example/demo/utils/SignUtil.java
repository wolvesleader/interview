package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;

public class SignUtil {

    /**
     * 获取签名信息
     * @param data
     * @return
     */
    public static String getSign(JSONObject data) {
        // 由于map是无序的，这里主要是对key进行排序（字典序）
        Set<String> keySet = data.keySet();
        String[] keyArr = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArr);
        StringBuilder sbd = new StringBuilder();
        for (String k : keyArr) {
            if (!StringUtils.isEmpty(data.getString(k))) {
                sbd.append(k + "=" + data.getString(k) + "&");
            }
        }
        return MD5Util.encode(sbd.toString(),"utf-8");
    }
}