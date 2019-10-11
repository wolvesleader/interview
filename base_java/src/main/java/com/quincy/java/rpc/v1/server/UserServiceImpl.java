package com.quincy.java.rpc.v1.server;

import com.quincy.java.rpc.v1.pojo.User;

/**
 * Created by quincy on 2018/7/9.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User save(String username,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
