package com.quincy.java.designpatterns.proxy.jdk;

/**
 * Created by quincy on 18/6/16.
 */
public class UserDao implements IUserDao{
    @Override
    public void save(String str) {
        System.out.println("userdao impl 1");
    }
}
