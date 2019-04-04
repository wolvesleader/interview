package com.quincy.java.rpc.v1.server;

import com.quincy.java.rpc.v1.pojo.User;

/**
 * Created by quincy on 2018/7/9.
 */
public interface UserService {

    public User save(String username, String password);
}
