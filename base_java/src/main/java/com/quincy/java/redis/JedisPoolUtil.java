package com.quincy.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.SetParams;

import java.util.Set;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: JedisPoolUtil
 *
 * @author: quincy
 * Date: 2020/6/11 上午10:49
 * History:
 */
public class JedisPoolUtil {
    private  JedisPool jedisPool;
    private int MAX_COUNT = 2;
    int CONNECTION_COUNT = 0;
    public JedisPoolUtil(){
        //多线程情况下会出现问题
        this.jedisPool = new JedisPool("49.234.30.116",45069);
    }

    /**
     * 确保连接一定会被关闭
     * @param callBackJedis
     */
    private void execute(CallBackJedis callBackJedis){
        Jedis jedis = jedisPool.getResource();
        //default
        jedis.auth("qiaoming","woshinimiaoye");
        //jedis.auth("")
        try{
            callBackJedis.call(jedis);
        }catch (JedisConnectionException e){
            //做三次尝试，如果三次都失败放弃连接
            if (CONNECTION_COUNT < MAX_COUNT){
                callBackJedis.call(jedis);
            }
        }finally {
            CONNECTION_COUNT =0;
            jedis.close();
        }
    }

    /**
     *
     * @param key 健
     * @param value 值
     * @param ttl 过期时间
     */
    public void setnx(String key ,String value,long ttl){
        execute(jedis -> jedis.set(key,value,SetParams.setParams().px(ttl)));
    }

    public String getnx(String key){
        Holder<String> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.get(key)));
        return holder.value();
    }


    public Long zadd(String key, double score, String member){
        Holder<Long> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zadd(key,score,member)));
        return holder.value();
    }

    public Long zrem(String key,String... members){
        Holder<Long> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zrem(key,members)));
        return holder.value();
    }


    public Set<String> zrevrangeBeforeAndAfter(String key,long start, long stop){
        Holder<Set<String>> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zrevrange(key,start,stop)));
        return holder.value();
    }

    public Double zincrby(String key,double increment, String member){
        Holder<Double> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zincrby(key,increment,member)));
        return holder.value();
    }

    public Long zrevrank(String key,String member){
        Holder<Long> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zrevrank(key,member)));
        return holder.value();
    }
    public Double zscore(String key,String member){
        Holder<Double> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zscore(key,member)));
        return holder.value();
    }

    /**
     * zset 递减排列
     * @param key
     * @return
     */
    public Set<Tuple> zrevrange(String key){
        Holder<Set<Tuple>> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zrevrangeWithScores(key,0,-1)));
        return holder.value();
    }

    public Set<Tuple> zrevrange(String key,long begin,long end){
        Holder<Set<Tuple>> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.zrevrangeWithScores(key,begin,end)));
        return holder.value();
    }

    public Response<Set<Tuple>> zrevrangePip(String key){
        Holder<Response<Set<Tuple>>> holder = new Holder<>();
        execute(jedis -> holder.value(jedis.pipelined().zrevrangeWithScores(key,0,-1)));
        return holder.value();
    }



}
