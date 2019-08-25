package com.quincy.jvm.jvm_memout.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * author:quincy
 * Date:2019-08-21
 */
@RestController
public class ThreadLocalController {

    final static ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();// 声明本地变量

    @RequestMapping(value = "/test0")

    public String test0(HttpServletRequest request) {
        new Thread() {
            public void run() {
                localVariable.set(new Byte[4096*1024]);// 为线程添加变量
                try {
                    Thread.sleep(2000);// 延迟线程生命周期，使得在其他线程调用 set 时，避免回收已经失去引用的 key 的 value
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }.start();
        return "success";
    }

}
