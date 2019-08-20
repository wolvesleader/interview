package com.quincy.tomcat_optimize.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author:quincy
 * Date:2019-08-19
 */
@Controller
public class UserController {

    @RequestMapping("/test/get")
    @ResponseBody
    public String get(){
        return "helloword";
    }
}
