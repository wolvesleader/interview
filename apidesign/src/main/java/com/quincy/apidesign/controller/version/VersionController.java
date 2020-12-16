package com.quincy.apidesign.controller.version;

import org.springframework.web.bind.annotation.*;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: VersionConteoller
 *
 * @author: quincy
 * Date: 2020/11/26 下午3:23
 * History:
 */
@RestController
@RequestMapping("version")
public class VersionController {

    /**
     * 通过URL Path实现版本控制
     * @return
     * /version/v1/api/user?name=123456
     *
     */
    @GetMapping("/v1/api/user")
    public String getName(){
        return "通过URL Path实现版本控制 v1";
    }

    @GetMapping("/v2/api/user")
    public String getName2(){
        return "通过URL Path实现版本控制 v2";
    }

    /**
     * 通过QueryString中的version参数实现版本控制
     * @param version
     * @return
     */
    @GetMapping(value = "/api/user", params = "version=1")
    public String getName3(@RequestParam("version") int version){
       return "通过QueryString中的version参数实现版本控制 v1";
    }

    //http://127.0.0.1:8080/version/api/user?version=2
    @GetMapping(value = "/api/user", params = "version=2")
    public String getName4(@RequestParam("version") int version){
        return "通过QueryString中的version参数实现版本控制 v2";
    }


    //通过请求头中的X-API-VERSION参数实现版本控制
    @GetMapping(value = "/api/user", headers = "X-API-VERSION=1")
    public String getName5(@RequestHeader("X-API-VERSION") int version) {
        return "通过请求头中的X-API-VERSION参数实现版本控制 v1";
    }


    @GetMapping(value = "/api/user", headers = "X-API-VERSION=2")
    public String getName6(@RequestHeader("X-API-VERSION") int version) {
        return "通过请求头中的X-API-VERSION参数实现版本控制 v2";
    }


    @GetMapping(value = "/api/user")
    @APIVersion("v1")
    public int getName7() {
        return 1;
    }


}
