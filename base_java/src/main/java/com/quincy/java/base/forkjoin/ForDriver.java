package com.quincy.java.base.forkjoin;

public class ForDriver {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        long sum = 0L;
        for (long i = 0; i < 100000000000L; i++) {
            sum += i;
        }
//46822
//43312
//34008
//34486
//31937
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间： " +(endTime-startTime)+ " 结果值 ：" + sum);
    }
}
