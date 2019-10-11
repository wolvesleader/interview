package com.quincy.java.base.iomodel.nio_reactor_thread;


import java.io.IOException;

/**
 * author:quincy
 * Date:2019-08-18
 * NIO+Reactor(反应堆)+单线程
 * 测试可以使用 telnet localhost 9999 命令开启多个窗口
 */
public class ReactorDriver {
    public static void main(String[] args) {
        try {
            int port = 9999;
            Reactor reactor  = new Reactor(port);
            new Thread(reactor).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
