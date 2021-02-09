package com.quincy.crawler.douban.pojo;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Ip
 * @author: quincy
 * @Date: 2021/2/9 下午6:52
 * @History:
 */

public class Ip {
    private String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Ip{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
