package com.quincy.apidesign.idempotence;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: IdempotenceSupportAdvice
 *
 * @author: quincy
 * Date: 2020/12/1 下午7:42
 * History:
 */
@Aspect
public class IdempotenceSupportAdvice {
    @Autowired
    private Idempotence idempotence;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.quincy.apidesign.idempotence.IdempotenceRequired)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //请求流水号
        String idempotenceId = request.getHeader("idempotenceId");

        boolean check = idempotence.check(idempotenceId);
        if (check){
            // TODO: 2020/12/1 返回重复对象，方便用户排查 
            // TODO: 2020/12/1 抛出重复异常
            new RuntimeException("操作重复");
        }
        //如果没有，需要把他存一下
        idempotence.save(idempotenceId);
        Object proceed = joinPoint.proceed();
        return proceed;
    }

}
