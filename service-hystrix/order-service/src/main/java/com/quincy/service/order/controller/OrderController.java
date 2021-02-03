package com.quincy.service.order.controller;

import com.quincy.service.order.pojo.Order;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * FileName: OrderController
 *
 * @author: quincy
 * Date: 2021/1/15 上午11:09
 * History:
 */
@RestController
@RequestMapping("/order/")
public class OrderController {


    @GetMapping("/get/{orderId}")
    public Order getOrder(@PathVariable("orderId") String orderId){
        Order order = new Order();
        try {
            //TimeUnit.SECONDS.sleep(RandomUtils.nextInt(5) + 1);
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        order.setNo(Integer.valueOf(orderId));
        return order;
    }

}
