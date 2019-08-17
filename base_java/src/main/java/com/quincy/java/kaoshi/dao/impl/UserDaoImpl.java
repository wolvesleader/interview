package com.quincy.java.kaoshi.dao.impl;

import com.quincy.java.kaoshi.utils.JdbcUtils;
import com.quincy.java.kaoshi.pojo.TwoUser;
import com.quincy.java.kaoshi.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * author:quincy
 * Date:2019-08-12
 */
public class UserDaoImpl implements UserDao {

    public boolean save(TwoUser user){
        try {
            Connection connection = JdbcUtils.getConnection();
            String sql = "insert into user(id,username,phone) values (null,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPhone());

            boolean execute = preparedStatement.execute();
            return execute;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
