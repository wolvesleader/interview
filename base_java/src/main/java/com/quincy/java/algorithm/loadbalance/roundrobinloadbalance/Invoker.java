package com.quincy.java.algorithm.loadbalance.roundrobinloadbalance;

/**
 * author:quincy
 * Date:2019-05-19
 */
public class Invoker {

    private String id;
    private String name;

    //权重
    private int weight;
    //dubbo超时，时间戳
    private long timestamp;

    //预热时间，对应dubbo delay="5000"配置
    private long warmup;


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getWarmup() {
        return warmup;
    }

    public void setWarmup(long warmup) {
        this.warmup = warmup;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
