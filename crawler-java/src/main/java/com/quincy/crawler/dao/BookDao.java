package com.quincy.crawler.dao;

import com.quincy.crawler.common.DbUtils;
import com.quincy.crawler.douban.pojo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: BookDao
 * @author: quincy
 * @Date: 2021/2/9 下午8:53
 * @History:
 */

public class BookDao {

    public boolean findByIsbn(String isbn){
        try {
            QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
            String sql = "select id from book where isbn = ?";
            Object[] params = new Object[]{isbn};
            Book query = queryRunner.query(sql, new BeanHandler<>(Book.class),params);
            return query == null;
        } catch (SQLException exception) {
            //exception.printStackTrace();
            return true;
        }
    }

    public void addBook(Book book){
        try {
                QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
                String sql = "insert into book (id,name,author,originalname,publish," +
                        "publishtime,bind,price,isbn,image,pagenumber,producers,series," +
                        "translator,star,type,contentInfo,authorInfo,catalogueinfo)" +
                        " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                Object[] params = new Object[]{null,book.getBookName(),book.getBookAuthor()
                ,book.getBookOriginalName(),book.getBookPublish(),book.getBookPublishTime()
                ,book.getBookBind(),book.getPrice(),book.getIsbn(),book.getImage()
                ,book.getPageNumber(),book.getBookProducers(),book.getBookSeries()
                ,book.getBookTranslator(),book.getDoubanStar(),book.getBookType()
                ,book.getBookContentInfo(),book.getBookAuthorInfo(),book.getBookCatalogue()};
                queryRunner.insert(sql,new ScalarHandler<>(),params);
            } catch (SQLException e) {
                e.printStackTrace();

            }
    }

    public Integer getLastIdFromRePullPath()  {
        try {
            QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
            String sql = "SELECT MAX(id) from re_pull_path";
            int update = queryRunner.update(sql);
            return update;
        } catch (SQLException exception) {
            //exception.printStackTrace();
            QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
            String sql = "SELECT count(id) from re_pull_path";
            try {
                Long query = queryRunner.query(sql, new ScalarHandler<Long>());
                return query.intValue();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

}
