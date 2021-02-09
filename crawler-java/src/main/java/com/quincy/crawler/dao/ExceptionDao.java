package com.quincy.crawler.dao;

import com.quincy.crawler.common.DbUtils;
import com.quincy.crawler.douban.pojo.Error;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: ExceptionDao
 * @author: quincy
 * @Date: 2021/2/9 下午8:58
 * @History:
 */

public class ExceptionDao {


    public Error getExceptionMsg(){
        //从数据库获取到数据
        //如果base和path相等，说明没有拿到具体的书籍信息，需要跟换ip继续执行
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
        /*try {
            douBanDriver.error = queryRunner.query("select * from error", new BeanHandler<Error>(Error.class));
            if (douBanDriver.error != null){
                String base = douBanDriver.error.getBase();
                String path = douBanDriver.error.getPath();
                if (base.equals(path)){
                    //需要切换IP和端口
                    douBanDriver.arrayList.remove(0);
                    queryRunner.update("delete from error where id = " + douBanDriver.error.getId());
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        return null;
    }
}
