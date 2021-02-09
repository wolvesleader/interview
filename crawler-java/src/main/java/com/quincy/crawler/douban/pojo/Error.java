package com.quincy.crawler.douban.pojo;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Error
 * @author: quincy
 * @Date: 2021/2/9 下午6:05
 * @History:
 */

public class Error {
    private int id;
    private String base;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
