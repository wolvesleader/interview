package com.quincy.java.designpatterns.template;

/**
 * author:quincy
 * Date:2019-06-06
 */
public abstract class BaseDao {

    public  void save(){
        System.out.println("save method");
    }

    public abstract String getById();



}
