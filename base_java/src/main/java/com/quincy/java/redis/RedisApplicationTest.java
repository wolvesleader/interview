package com.quincy.java.redis;

import org.junit.Test;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: RedisApplication
 *
 * @author: quincy
 * Date: 2020/11/14 下午2:24
 * History:
 * 统计全部玩家的排行榜
 * 按名次查询排名前 N 名的玩家
 * 查询某个玩家的分数
 * 查询某个玩家的排名
 * 对玩家的分数和排名进行更新
 * 查询指定玩家前后 M 名的玩家
 * 增加或移除某个玩家，并对排名进行更新
 *
    --设置时间种子
    math.randomseed(ARGV[1])
    -- 设置初始的生成时间
    local create_time = 1567769563 - 3600*24*365*2.0
    local num = ARGV[2]
    local user_id = ARGV[3]
    for i=1, num do
    --生成1到60之间的随机数
    local interval = math.random(1, 60)
    --产生1到112之间的随机数
    local temp = math.random(1, 112)
    if (temp == 112) then
    --产生0到100之间的随机数
    temp = temp + math.random(0, 100)
    end
    create_time = create_time + interval
    temp = temp + create_time / 10000000000
    redis.call('ZADD', KEYS[1], temp, user_id+i-1)
    end
    return 'Generation Completed'
 */

public class RedisApplicationTest {



    /**
     * 增加或移除某个玩家，并对排名进行更新
     */
    @Test
    public void addOrUpdatePayerUpdateRank(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Long user_score = jedisPoolUtil.zrem("user_score", new String[]{String.valueOf(10001)});
        System.out.println(user_score);
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间:" + String.valueOf(endTime-startTime));
    }

    /**
     * 查询指定玩家前后 M 名的玩家
     */
    @Test
    public void findPayerAfterAndBefore(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Set<String> userScore = jedisPoolUtil.zrevrangeBeforeAndAfter("user_score",18031,18041);
        userScore.stream().forEach(item ->{
            System.out.println(item);
        });
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间:" + String.valueOf(endTime-startTime));
    }

    /**
     * 对玩家的分数和排名进行更新
     */
    @Test
    public void updatePayerRankAndScore(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Double userScore = jedisPoolUtil.zincrby("user_score",-1,String.valueOf(10001));
        System.out.println(userScore);
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间:" + String.valueOf(endTime-startTime));
    }

    /**
     * 查询某个玩家的排名
     */
    @Test
    public void findPayerRank(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Long userScore = jedisPoolUtil.zrevrank("user_score",String.valueOf(10001));
        System.out.println(userScore);
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间:" + String.valueOf(endTime-startTime));
    }

    /**
     * 查询某个玩家的分数
     */
    @Test
    public void findPayerScore(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Double userScore = jedisPoolUtil.zscore("user_score",String.valueOf(10001));
        System.out.println(userScore);
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间:" + String.valueOf(endTime-startTime));
    }

    /**
     * 按名次查询排名前 N 名的玩家
     */
    @Test
    public void findBeforeN(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Set<Tuple> userScore = jedisPoolUtil.zrevrange("user_score",0,9);
        userScore.stream().forEach(item -> {
            System.out.println(item.getElement() + " = " + item.getScore());
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }




    /**
     * 统计全部玩家的排行榜 降序
     * 耗费时间 28375
     * 28733
     */
    @Test
    public void findAll(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Set<Tuple> userScore = jedisPoolUtil.zrevrange("user_score");
        userScore.stream().forEach(item -> {
            System.out.println(item.getElement() + " = " + item.getScore());
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    /**
     * 耗费时间
     * 28246
     * 28249
     * 28344
     * 28520
     */
    @Test
    public void findAllWithPip(){
        long startTime = System.currentTimeMillis();
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Response<Set<Tuple>> userScore = jedisPoolUtil.zrevrangePip("user_score");
        Set<Tuple> tuples = userScore.get();
        tuples.stream().forEach(item -> {
            System.out.println(item.getElement() + " = " + item.getScore());
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

}
