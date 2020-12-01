package com.quincy.apidesign.idempotence;

import com.quincy.apidesign.hidepath.JedisPoolUtil;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: RedisIdempotenceImpl
 *
 * @author: quincy
 * Date: 2020/12/1 下午7:56
 * History:
 */
@Component
public class RedisIdempotenceImpl implements Idempotence{
    @Override
    public boolean check(String idempotenceId) {
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        String idempotence = jedisPoolUtil.getnx("idempotence");
        return idempotence == null;
    }

    @Override
    public void save(String idempotenceId) {
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        jedisPoolUtil.setnx("idempotence",idempotenceId,100000L);
    }
}
