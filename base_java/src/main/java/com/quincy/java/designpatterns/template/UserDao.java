package com.quincy.java.designpatterns.template;

/**
 * author:quincy
 * Date:2019-06-06
 */
public class UserDao extends BaseDao {
    @Override
    public String getById() {
        return "更具用户id查询";
    }
}
