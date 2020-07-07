package com.example.demo.aspect;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.SignUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate;



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
        String username = request.getHeader("username");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(sign)) {
            return "参数不能为null";
        }

        //校验请求是否超时 60秒
        long time = 60;
        long now = System.currentTimeMillis() / 1000;
        if (now - Long.valueOf(timestamp) > time) {
            return "请求超时";
        }

        //校验请求是否重复
        boolean signCheck = stringRedisTemplate.opsForHash().hasKey("sign_check", "sign_check" + nonce);
        if (signCheck) {
            return "请不要发送重复的请求";
        } else {
            // 如果nonce没有存在缓存中，则加入，并设置失效时间（秒）
            HashOperations<String, Object, Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
            stringObjectObjectHashOperations.put("sign_check", "sign_check" + nonce, nonce);
            stringRedisTemplate.expire("sign_check",time, TimeUnit.SECONDS);
        }



        JSONObject signObj = new JSONObject();
        signObj.put("timestamp", timestamp);
        signObj.put("nonce", nonce);
        signObj.put("username", username);
        String mySign = SignUtil.getSign(signObj);
        if (!mySign.equals(sign)){
            return "接口签名错误";
        }
        Object result = pjp.proceed();
        return result;
    }





}