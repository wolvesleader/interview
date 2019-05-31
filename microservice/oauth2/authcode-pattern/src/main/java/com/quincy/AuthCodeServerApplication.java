package com.quincy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthCodeServerApplication {
	//授权码登陆方式一般用于第三方登陆

	//先去第三方服务器做认证
	//认证完成之后第三方服务器返回授权页面
	//用户进行授权
	//http://localhost:8080/oauth/authorize?client_id=clientapp&response_type=code&redirect_uri=http://localhost:8080/callback&scope=read_userinfo
	//授权通过之后，返回认证服务器返回的授权码
	//http://localhost:8080/callback?code=pUHZEN code就是授权码
	//获取到授权码之后，携带授权码去申请令牌
	//使用postman获取token令牌
	// http://localhost:8080/oauth/token
	//记得选basic auth 填写用户名和密码 用户名为withClient("clientapp")密码为secret("qing")
	//获取令牌，携带令牌访问资源服务器的资源
	//使用postman 模拟浏览器访问资源 http://localhost:8080/api/userinfo
	public static void main(String[] args) {
		SpringApplication.run(AuthCodeServerApplication.class, args);
	}
}
