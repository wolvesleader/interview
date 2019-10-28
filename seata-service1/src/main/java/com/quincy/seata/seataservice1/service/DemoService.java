package com.quincy.seata.seataservice1.service;

import com.quincy.seata.seataservice1.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:quincy
 * Date:2019-10-11
 */
@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    //事务注解
    public void test(){
        demoDao.insert("test");

       // HttpClient
    }
}
