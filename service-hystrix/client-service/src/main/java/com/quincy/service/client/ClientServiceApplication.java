package com.quincy.service.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * hystrix作用
 * 1.超时控制（超时源码分析）
 * 2.备用方案 ，降级
 * 3.线程池隔离 舱壁模式
 * 4.
 * 5.
 * 6.
 * 7.
 * 8.
 *
 */
/*@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker*/
@SpringCloudApplication
public class ClientServiceApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

}
