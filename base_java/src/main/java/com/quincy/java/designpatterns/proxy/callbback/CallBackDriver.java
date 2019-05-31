package com.quincy.java.designpatterns.proxy.callbback;

/**
 * author:quincy
 * Date:2019-05-31
 */
public class CallBackDriver {

    public void get(IUserDao iUserDao){
        iUserDao.get("str");
    }

    public static void main(String[] args) {

        CallBackDriver callBackDriver = new CallBackDriver();
        callBackDriver.get(new IUserDao() {
            @Override
            public void get(String name) {
                System.out.println(name);
            }
        });
    }
}
