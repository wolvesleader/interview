package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.SignUtil;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RestTemplate restTemplate ;

    long timestamp = System.currentTimeMillis();
    int nonce = (int) (Math.random()*10000);

    public String interfaceSignRule(){
        //客户端通过json构建timestamp和nonce随机数
        //对timestamp，nonce要做排序
        //调用getSign方法对接口进行签名
        JSONObject signObj = new JSONObject();
        signObj.put("timestamp", timestamp);
        signObj.put("nonce", nonce);
        String sign = SignUtil.getSign(signObj);
        return sign;
    }

    @Test
    void contextLoads() {
        String sign = interfaceSignRule();
        HttpHeaders headers  = new HttpHeaders();
        headers.add("timestamp", String.valueOf(timestamp));
        headers.add("nonce", String.valueOf(nonce));
        headers.add("sign", sign);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> resultEntity = restTemplate.exchange("http://localhost:9999//demo/test", HttpMethod.GET, requestEntity, String.class);
        String body = resultEntity.getBody();
        System.out.println(body);


    }



}
