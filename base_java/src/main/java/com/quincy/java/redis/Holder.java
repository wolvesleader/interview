package com.quincy.java.redis;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: JedisHolder
 *
 * @author: quincy
 * Date: 2020/6/11 下午12:46
 * History:
 */

public class Holder<T> {
    private T value;

    public T value(){
        return value;
    }

    public void value (T value){
        this.value = value;
    }
}
