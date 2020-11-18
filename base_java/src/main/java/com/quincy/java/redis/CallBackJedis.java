package com.quincy.java.redis;


import redis.clients.jedis.Jedis;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: CallBackJedis
 *
 * @author: quincy
 * Date: 2020/6/11 上午10:56
 * 提供接口的原因是防止其他人忘记关闭连接
 * History:
 */
public interface CallBackJedis {
    public void call(Jedis jedis);
}
