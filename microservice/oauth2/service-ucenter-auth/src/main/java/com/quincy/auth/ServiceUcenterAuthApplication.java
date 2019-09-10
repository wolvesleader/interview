package com.quincy.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * springboot2.x 和spring5完成密码模式
 *
 */
@SpringBootApplication
public class ServiceUcenterAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterAuthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
