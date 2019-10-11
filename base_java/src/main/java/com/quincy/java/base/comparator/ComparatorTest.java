package com.quincy.java.base.comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * author:quincy
 * Date:2019-07-28
 */
public class ComparatorTest {
    public static void main(String[] args){
        List<People> list = new ArrayList<People>();
        list.add( new People("quincy" ,34));
        list.add( new People("admin" ,67));
        list.add( new People("abc" ,67));
        list.add( new People("zero" ,67));
        list.add( new People("ping" ,67));
        list.add( new People("pust" ,67));
        for(People per : list){
            System. out.println(per + "排序前" );
        }
        java.util.Collections. sort(list,new PeopleComparator());
        for(People per : list){
            System. out.println(per + "==排序后==" );
        }
        People p = new People("dd" ,12);
        People p2 = new People("dd" ,12);
        System. out.println(p.equals(p2));
    }
}
