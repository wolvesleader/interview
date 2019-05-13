package com.quincy.java.redis;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author:quincy
 * Date:2019-05-13
 */
@Component("redisBloomFilter")
public class RedisBloomFilter {

    //预计插入的数据，过滤器的长度
    private long expectedInsertions = 100000;

    //容错率
    private double fpp = 0.001;

    //存入数据的前缀
    private String redisKeyPrefix = "qbf:";

    @Autowired
    private RedisTemplate  redisTemplate;

    public void setExpectedInsertions(long expectedInsertions) {
        this.expectedInsertions = expectedInsertions;
    }

    public void setFpp(double fpp) {
        this.fpp = fpp;
    }

    public void setRedisKeyPrefix(String redisKeyPrefix) {
        this.redisKeyPrefix = redisKeyPrefix;
    }

    //计算bit数组的最佳长度
    private long numBit = optimalNumOfBit(expectedInsertions,fpp);

    //计算bit数组的最佳长度
    private long optimalNumOfBit(long expectedInsertions, double fpp) {
        if (fpp == 0){
            fpp = Double.MIN_VALUE;
        }
        return (long)(-expectedInsertions*Math.log(fpp) / (Math.log(2) * Math.log(2)));
    }

    //计算hash函数的个数
    private int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions,numBit);

    private int optimalNumOfHashFunctions(long expectedInsertions, long numBit) {
        return Math.max(1,(int)(Math.round((double)numBit/expectedInsertions * Math.log(2))));
    }

    //计算hash值的偏移量，在位图中的存放位置
    long[] murmurHashOffset(String value) {
        long[] offset = new long[numHashFunctions];

        long hash64 = Hashing.murmur3_128().hashObject(value, null).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            offset[i - 1] = nextHash % expectedInsertions;
        }

        return offset;
    }

    //给过滤器添加预热值
    public void put(String key){
        long[] indexs = murmurHashOffset(key);
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                redisConnection.openPipeline();
                for (long index : indexs) {
                    redisConnection.setBit((redisKeyPrefix+ "1").getBytes(),index,true);
                }
                return null;
            }
        });
    }

    //从过滤器中获取值
    public boolean isExist(String key){
        long[] indexs = murmurHashOffset(key);
        List list = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                redisConnection.openPipeline();
                for (long index : indexs) {
                    redisConnection.getBit((redisKeyPrefix+ "1").getBytes(),index);
                }
                return null;
            }
        });

        return !list.contains(false);
    }

}
