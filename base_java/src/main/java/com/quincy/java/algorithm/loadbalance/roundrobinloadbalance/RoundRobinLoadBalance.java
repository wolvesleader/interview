package com.quincy.java.algorithm.loadbalance.roundrobinloadbalance;

import org.aopalliance.intercept.Invocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * author:quincy
 * Date:2019-05-19
 * 负载均衡
 * 轮训算法
 */
public class RoundRobinLoadBalance {
    public static final String NAME = "roundrobin";

    //记录所有提供服务的数据，<serviceKey+methodName, 调用次数> 和该服务被调用的次数
    private final ConcurrentMap<String, AtomicPositiveInteger> sequences = new ConcurrentHashMap<String, AtomicPositiveInteger>();

    protected Invoker doSelect(List<Invoker> invokers, Invocation invocation) {
        String key = invokers.get(0).getId() + "." + invokers.get(0).getName();
        int length = invokers.size(); // 总个数
        int maxWeight = 0; // 最大权重
        int minWeight = Integer.MAX_VALUE; // 最小权重
        //记录每个实例以及对应的权重
        final LinkedHashMap<Invoker, IntegerWrapper> invokerToWeightMap = new LinkedHashMap<Invoker, IntegerWrapper>();
        //总的权重
        int weightSum = 0;
        //遍历每一个实例
        for (int i = 0; i < length; i++) {
            //获取对应实例的权重值
            int weight = getWeight(invokers.get(i), invocation);
            maxWeight = Math.max(maxWeight, weight); // 累计最大权重
            minWeight = Math.min(minWeight, weight); // 累计最小权重
            // 只添加有权重的实例
            if (weight > 0) {
                invokerToWeightMap.put(invokers.get(i), new IntegerWrapper(weight));
                weightSum += weight;
            }
        }
        //获取该服务的调用次数获取该服务的调用次数
        AtomicPositiveInteger sequence = sequences.get(key);
        if (sequence == null) {
            //没有调用记录则添加数据
            sequences.putIfAbsent(key, new AtomicPositiveInteger());
            sequence = sequences.get(key);
        }
        //调用次数加一
        int currentSequence = sequence.getAndIncrement();

        if (maxWeight > 0 && minWeight < maxWeight) { // 权重不一样
            //将调用次数 % 权重总数，得出偏移量 mod
            int mod = currentSequence % weightSum;
            // 遍历最大的权重值，
            // 为什么会遍历它?
            // 因为每一次循环就遍历所有的实例，一个实例最大的权重为 maxWeight，
            // 最多遍历 maxWeight 次所有实例就可以找到想要的实例
            for (int i = 0; i < maxWeight; i++) {
                for (Map.Entry<Invoker, IntegerWrapper> each : invokerToWeightMap.entrySet()) {
                    final Invoker k = each.getKey();
                    final IntegerWrapper v = each.getValue();
                    // mod 为 0 表示选中了，但要满足该实例的权重大于 0
                    if (mod == 0 && v.getValue() > 0) {
                        return k;
                    }
                    // 实例没选中，则权重减 1，相当于选中机会少了
                    if (v.getValue() > 0) {
                        v.decrement();
                        // 偏移量也减 1
                        mod--;
                    }
                }
            }
        }
        // 取模轮循
        return invokers.get(currentSequence % length);
    }

    protected int getWeight(Invoker invoker, Invocation invocation) {
        //获取权重
        int weight = invoker.getWeight();
        if (weight > 0) {
            //dubbo运行时间,dubbo第一次发布的时间
            long timestamp = invoker.getTimestamp();
            if (timestamp > 0L) {
                int uptime = (int) (System.currentTimeMillis() - timestamp);
                int warmup = (int)invoker.getWarmup();
                //如果没有超时，运行之间没有到达预热时间，权限开始慢慢提升
                if (uptime > 0 && uptime < warmup) {
                    weight = calculateWarmupWeight(uptime, warmup, weight);
                }
            }
        }
        return weight;
    }
    //处于预热期的提供者，权重降级
    static int calculateWarmupWeight(int uptime, int warmup, int weight) {
        int ww = (int) ((float) uptime / ((float) warmup / (float) weight));
        return ww < 1 ? 1 : (ww > weight ? weight : ww);
    }

    private static final class IntegerWrapper {
        private int value;

        public IntegerWrapper(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void decrement() {
            this.value--;
        }
    }
}
