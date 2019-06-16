package com.quincy.java.designpatterns.adapter;

/**
 * author:quincy
 * Date:2019-06-16
 *
 */
public abstract class BaseDao implements IBaseDao{

    public void update(){
        System.out.println("update");
    }
    public void save(){
        System.out.println("save");
    }
    public Object find(){
        return "find";
    }
    public void delete(){
        System.out.println("delete");
    }


}
