package com.quincy.apidesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author quincy
 * 接口安全相关问题
 * 1.接口开发规范
 * 2.接口文档
 * 3.接口安全
 *   3.1裸奔
 *   3.2生成简单的token
 *   3.3数据加密
 *   3.4数据加签
 *   3.5时间戳机制
 *   3.6AppId机制
 *   3.7限流机制(getway网管限流、nginx限流、自定义限流等等)
 *   3.8黑名单机制
 *   3.9安全系数要求极高的可以考虑发送手机短信，确保是本人操作，例如转账操作
 *   3.10其他一些安全框架 springsecurity认证授权框架，可以只用认证功能
 *   3.11Shiro oauth2等等 jti
 *   3.12jwt和sha提供的token的区别
 *   3.13 https协议请求
 *   我们案例实现方式 数据加签+时间戳机制+AppId机制
 * 4.接口参数校验(数据合法性校验) 和接口签名校验
 * 5.接口幂等性实现
 *   5.1幂等性的概念
 *   get null 1
 *   delete
 *   post save user dubbo request1 request1
 *   例如 dubbo的重试机制会造成给数据库中插入多条语句等等
 *   5.2幂等性的实现方式
 *   我们的实现方式是添加请求流水号(请求标识)
 * 6.防止暴力刷接口
 *   图形码验证
 *   短信验证
 *   redis限流
 * 7.隐藏接口地址 并发非常高
 *   例如 做秒杀业务的时候，在秒杀开始之前没必要让用户知道你的接口的真实地址
 *        这种情况下可以考虑隐藏接口地址
 * 问题：post body数据劫持
 */
@SpringBootApplication
public class ApiDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDesignApplication.class, args);
    }

}
