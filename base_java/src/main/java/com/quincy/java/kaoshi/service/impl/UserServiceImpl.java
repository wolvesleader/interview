package com.quincy.java.kaoshi.service.impl;

import com.quincy.java.kaoshi.pojo.TwoUser;
import com.quincy.java.kaoshi.dao.UserDao;
import com.quincy.java.kaoshi.dao.impl.UserDaoImpl;
import com.quincy.java.kaoshi.service.UserService;

/**
 * author:quincy
 * Date:2019-08-12
 */
public class UserServiceImpl implements UserService {

    public boolean save(TwoUser user){
        UserDao userDao = new UserDaoImpl();
        boolean save = userDao.save(user);
        return save;
    }
}
