package com.quincy.java.tx;

/**
 * author:quincy
 * Date:2019-03-20
 */
public class UserServiceDriver {

    UserService userService1;

    public static void main(String[] args) {
        //早晨
        UserServiceImpl userService = new UserServiceImpl();
        //
        UserServiceImpl2 userServiceImpl2 = new UserServiceImpl2();
        UserService userService1 = new UserServiceImpl();

    }
}
