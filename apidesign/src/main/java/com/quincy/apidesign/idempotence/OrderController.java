package com.quincy.apidesign.idempotence;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: OrderController
 *
 * @author: quincy
 * Date: 2020/12/1 下午7:37
 * History:
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @IdempotenceRequired
    @RequestMapping("createOrder")
    public Order createOrder(HttpServletRequest request,String idempotenceId) {
        return new Order();
    }



}
