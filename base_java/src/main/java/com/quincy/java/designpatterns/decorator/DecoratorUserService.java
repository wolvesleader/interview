package com.quincy.java.designpatterns.decorator;

/**
 * Created by quincy on 2018/12/20.
 */
public class DecoratorUserService {

    private UserService userService;

    public DecoratorUserService(UserService userService){
        this.userService = userService;
    }

    public void  save1(){
        System.out.println("增强之后的save方法");
        //保留原来方法的功能
        System.out.println("原来的方法");
    }
}
