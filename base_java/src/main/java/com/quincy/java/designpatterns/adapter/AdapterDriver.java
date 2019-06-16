package com.quincy.java.designpatterns.adapter;

/**
 * author:quincy
 * Date:2019-06-16
 * 适配器模式
 *需求
 * 1.BaseDao不能被改变，是别人写的类，我们不能随意改动
 * 2.现在需要添适配一个findByPage的方法
 * 3.现在可以单独写一个类，实现IBaseDao接口
 * 4.有以下的问题单独实现类会造成代码重复
 * 5.需要把查询所有数据，转换为分页查询的方式
 */
public class AdapterDriver {


    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.findByPage();
    }

}
