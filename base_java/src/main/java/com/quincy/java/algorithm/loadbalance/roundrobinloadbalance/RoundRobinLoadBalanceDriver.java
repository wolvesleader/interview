package com.quincy.java.algorithm.loadbalance.roundrobinloadbalance;

import java.util.ArrayList;

/**
 * author:quincy
 * Date:2019-05-19
 */
public class RoundRobinLoadBalanceDriver {
    public static void main(String[] args) {
        RoundRobinLoadBalance roundRobinLoadBalance = new RoundRobinLoadBalance();
        ArrayList<Invoker> arrayList = new ArrayList<>();
        Invoker invoker = new Invoker();
        invoker.setId("1001");
        invoker.setName("number1");
        invoker.setTimestamp(5000);
        invoker.setWarmup(30000);
        invoker.setWeight(1);
        arrayList.add(invoker);

        Invoker invoker2 = new Invoker();
        invoker2.setId("1002");
        invoker2.setName("number2");
        invoker2.setTimestamp(5000);
        invoker2.setWarmup(30000);
        invoker2.setWeight(10);
        arrayList.add(invoker2);

        Invoker invoker3 = new Invoker();
        invoker3.setId("1003");
        invoker3.setName("number3");
        invoker3.setTimestamp(5000);
        invoker3.setWarmup(30000);
        invoker3.setWeight(10);
        arrayList.add(invoker3);

        for (int i = 0; i < 10; i++) {
            Invoker selectInvoker = roundRobinLoadBalance.doSelect(arrayList, null);
            System.out.println(selectInvoker.getId());
        }


    }
}
