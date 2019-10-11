package com.quincy.java.base.equals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author:quincy
 * Date:2019-07-26
 */
public class EqualsDriver {
    public static void main(String[] args) {
        System.out.println();


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("888");
        List<String> list = Collections.synchronizedList(arrayList);
    }
}
