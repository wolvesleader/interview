package com.quincy.service.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.misc.Unsafe;

import java.util.List;

/**
 * Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * FileName: CilentController
 *
 * @author: quincy
 * Date: 2021/1/15 上午10:50
 * History:
 * client 发起order下单
 * order  调用goods查询商品，生成订单
 *
 */

@RestController
@RequestMapping("/client")
public class CilentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     *
     * 配置超时直接返回
     * @param orderId
     * @return
     */
    @HystrixCommand(
            commandProperties = {
               @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4352000")
            }
    )
    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId){
        String url = "http://ORDER-SERVCIE/order/get/{orderId}";
        String forObject = restTemplate.getForObject(url, String.class, orderId);
        return forObject;
    }


    /**
     * 服务执行出错误，会走备用方案
     * @param orderId
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "fallbackMethod"
    )
    @GetMapping("/order/fallbackMethod/{orderId}")
    public String getOrderFallback(@PathVariable("orderId") String orderId){
        String url = "http://ORDER-SERVCIE/order/get/{orderId}";
        String forObject = restTemplate.getForObject(url, String.class, orderId);
        return forObject;
    }

    public String fallbackMethod(@PathVariable("orderId") String orderId){
        return "服务出错备用方案";
    }


}
