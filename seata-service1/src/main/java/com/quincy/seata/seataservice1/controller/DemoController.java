package com.quincy.seata.seataservice1.controller;

import com.quincy.seata.seataservice1.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:quincy
 * Date:2019-10-11
 */
@RestController
@RequestMapping("/service1")
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/test")
    public void test(){
        demoService.test();
    }
}
