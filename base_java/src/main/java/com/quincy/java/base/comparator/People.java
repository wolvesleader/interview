package com.quincy.java.base.comparator;

import java.util.Comparator;

/**
 * author:quincy
 * Date:2019-07-28
 */
public class People{
    String name;
    int age;

    public People(){}

    public People(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return this .name + "  " + this.age;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + (( name == null) ? 0 : name .hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true ;
        if (obj == null )
            return false ;
        if (getClass() != obj.getClass())
            return false ;
        People other = (People) obj;
        if (age != other.age)
            return false ;
        if (name == null) {
            if (other.name != null)
                return false ;
        } else if (!name .equals(other.name))
            return false ;
        return true ;
    }
}
class PeopleComparator implements Comparator<People> {
    //比较的规则是写在一个独立的类里边的，不是写在要比较的People类里边的
    @Override
    public int compare(People o1, People o2) {
        //return this.age > p.age ? 1 : (this.age < p.age ? -1 : 0);
        return o1.name .compareTo(o2.name);//对象按照字母顺序排序
    }
}
