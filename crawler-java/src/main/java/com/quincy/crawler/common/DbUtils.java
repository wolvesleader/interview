package com.quincy.crawler.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * author:quincy
 * Date:2019-07-11
 */
public class DbUtils {


    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            properties.load(DbUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource  getDataSource(){
        return  dataSource;
    }


}
