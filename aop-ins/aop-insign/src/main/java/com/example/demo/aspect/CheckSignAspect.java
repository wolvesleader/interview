package com.example.demo.aspect;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.SignUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Set;

/**
 * @author quincy
 */
@Aspect
@Component
public class CheckSignAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @Pointcut("@annotation(com.example.demo.utils.Sign)")
    public void excudeService() {}


    /**
     * 通过aop做接口签名校验
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        String timestamp = request.getHeader("timestamp");
        System.out.println(timestamp);
        String nonce = request.getHeader("nonce");
        String sign = request.getHeader("sign");
        if (StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(sign)) {
            return "参数不能为null";
        }

        JSONObject signObj = new JSONObject();
        signObj.put("timestamp", timestamp);
        signObj.put("nonce", nonce);
        String mySign = SignUtil.getSign(signObj);
        if (!mySign.equals(sign)){
            return "接口签名错误";
        }
        Object result = pjp.proceed();
        return result;
    }





}