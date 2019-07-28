package com.quincy.java.base.comparator;

/**
 * author:quincy
 * Date:2019-07-28
 */
public class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(){}

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Person p) {
        //直接定义在要比较的类的内部
        //return this.age > p.age ? 1 : (this.age < p.age ? -1 : 0);
        return this .name .compareTo(p.name);//对象按照字母顺序排序

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
        Person other = (Person) obj;
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
