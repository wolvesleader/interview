package com.quincy.java.designpatterns.adapter;

/**
 * author:quincy
 * Date:2019-06-16
 */
public interface IBaseDao {
    public void update();
    public void save();
    public Object find();
    public void delete();
}
