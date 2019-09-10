package com.quincy.implicit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 简化模式
 */
@SpringBootApplication
public class ImplicitServerApplication {


    //http://localhost:8080/oauth/authorize?client_id=clientapp&response_type=token&redirect_uri=http://localhost:8080/callback&scope=read_userinfo
    //访问网址会出现认证页面，输入application.properties中正确的账户和密码之后
    //会出现授权页面
    //点击授权
    //http://localhost:8080/callback#access_token=8c98d22c-27ab-49f7-be60-eab6eefc33f3&token_type=bearer&expires_in=119
    //就是令牌相比较与授权码模式少了一步获取授权码的步骤
    //access_token=8c98d22c-27ab-49f7-be60-eab6eefc33f3
    public static void main(String[] args) {
        SpringApplication.run(ImplicitServerApplication.class, args);
    }
}
