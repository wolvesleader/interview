package com.quincy.java.designpatterns.template;

/**
 * author:quincy
 * Date:2019-06-06
 */
public class OrderDao extends BaseDao {
    @Override
    public String getById() {
        return "根据订单id查询";
    }
}
