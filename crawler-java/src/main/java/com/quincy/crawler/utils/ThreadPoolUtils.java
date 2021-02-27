package com.quincy.crawler.utils;

import com.quincy.crawler.common.DbUtils;
import com.quincy.crawler.dao.BookDao;
import com.quincy.crawler.douban.pojo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: ThreadPoolUtils
 * @author: quincy
 * @Date: 2021/2/10 上午6:48
 * @History:
 */

public class ThreadPoolUtils {


    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,8, 7000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(200));

    private static final ThreadPoolExecutor threadPoolExecutorInsertBook = new ThreadPoolExecutor(20,30, 6000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(400));

    /**
     * 生成插入数据的id值
     */
    //public static  AtomicInteger atomicInteger = new AtomicInteger();

    public static void execute(String path){
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
                    String sql = "insert into re_pull_path (id,path) values (?,?) ";
                    //int id = atomicInteger.incrementAndGet();
                    Object[] params = new Object[]{null,path};
                    queryRunner.insert(sql,new ScalarHandler<>(),params);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

    public static void executeBook(Book book){
        threadPoolExecutorInsertBook.allowCoreThreadTimeOut(true);
        threadPoolExecutorInsertBook.submit(new Runnable() {
            @Override
            public void run() {
                BookDao bookDao = new BookDao();
                //存入之前先查询一下该书，如果是已经有了，不需要插入
                //boolean byIsbn = bookDao.findByIsbn(book.getIsbn());
                //if (byIsbn){
                    bookDao.addBook(book);
               // }
            }
        });

    }




}
