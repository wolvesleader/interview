package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.Sign;
import com.example.demo.utils.SignUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: TestController
 *
 * @author: quincy
 * Date: 2020/7/2 下午5:20
 * History:
 */
@RestController
@RequestMapping("/demo")
public class TestController {

    @RequestMapping("/test")
    @Sign
    public String test(){
        return "test method";
    }

    /**
     * 通过接口获取签名
     * @param timestamp
     * @param nonce
     * @return
     */
    @RequestMapping("/getSign")
    public String getSign(String timestamp,String nonce){
        JSONObject signObj = new JSONObject();
        signObj.put("timestamp", timestamp);
        signObj.put("nonce", nonce);
        String sign = SignUtil.getSign(signObj);
        return sign;
    }

}
