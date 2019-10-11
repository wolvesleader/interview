package com.quincy.java.base.comparator;

/**
 * author:quincy
 * Date:2019-07-28
 */


import java.util.ArrayList;
import java.util.List;
public class ComparableTest {
    public static void main(String[] args){
        List<Person> list = new ArrayList<Person>();
        list.add( new Person("quincy" ,34));
        list.add( new Person("admin" ,67));
        list.add( new Person("abc" ,67));
        list.add( new Person("zero" ,67));
        list.add( new Person("ping" ,67));
        list.add( new Person("pust" ,67));
        for(Person per : list){
            System. out.println(per + "排序前" );
        }
        java.util.Collections. sort(list);
        for(Person per : list){
            System. out.println(per + "==排序后==" );
        }
        Person p = new Person("dd" ,12);
        Person p2 = new Person("dd" ,12);
        System. out.println(p.equals(p2));
    }
}
