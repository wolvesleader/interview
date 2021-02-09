package com.quincy.crawler.dao;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: BookDao
 * @author: quincy
 * @Date: 2021/2/9 下午8:53
 * @History:
 */

public class BookDao {

    public void addBook(){
        /*try {
                QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
                String sql = "insert into book (bookId,bookName,bookAuthor,bookOriginalName,bookPublish," +
                        "bookPublishTime,bookBind,price,isbn,image,pageNumber,bookProducers,bookSeries," +
                        "bookTranslator,doubanStar,bookType,bookContentInfo,bookAuthorInfo,bookCatalogue)" +
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
                break;
            }*/
    }
}
