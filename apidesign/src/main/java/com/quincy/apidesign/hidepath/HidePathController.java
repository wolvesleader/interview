package com.quincy.apidesign.hidepath;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: HidePathController
 *
 * @author: quincy
 * Date: 2020/12/1 下午6:49
 * History:
 */

@RestController
@RequestMapping("hidepath")
public class HidePathController {

    @RequestMapping("getpath")
    public String getHidePath(){
        //创建path
        String path = UUID.randomUUID().toString().replace("-","");
        //把路径保存在redis中
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        jedisPoolUtil.setnx("sellkill:hidepath",path,1000000L);
        return path;
    }

    @RequestMapping("/{path}/sellkill")
    public String sellkill(@PathVariable("path") String path){
        //检查path
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        String chackpath = jedisPoolUtil.getnx("sellkill:hidepath");
        if (chackpath == null || !chackpath.equals(path)){
            return "接口请求错误";
        }
        // TODO: 2020/12/1  
        // TODO: 2020/12/1 正常的秒杀业务
        return "success";
    }


}
