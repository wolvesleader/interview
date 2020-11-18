package com.quincy.java.base;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Demo
 *
 * @author: quincy
 * Date: 2020/11/16 下午5:11
 * History:
 */

public class Demo {
    public static void main(String[] args) {
        int[] a = {2, 3, 5};
        for (int i : a) {
            System.err.println(i);
            System.out.println(a[i + 1]);
        }
    }
}
