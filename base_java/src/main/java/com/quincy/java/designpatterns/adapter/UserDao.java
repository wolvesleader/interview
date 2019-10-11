package com.quincy.java.designpatterns.adapter;

/**
 * author:quincy
 * Date:2019-06-16
 */
public class UserDao extends BaseDao implements IUserDao {

    //适配findByPage方法
    @Override
    public void findByPage() {

        find();
        System.out.println("适配find方法的findByPage方法");
        //获取到所有的数据之后在分页
    }
}
