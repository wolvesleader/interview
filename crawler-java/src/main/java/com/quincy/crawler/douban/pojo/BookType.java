package com.quincy.crawler.douban.pojo;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: BookType
 * @author: quincy
 * @Date: 2021/2/21 上午2:49
 * @History:
 */

public class BookType {
    private String bookType;
    private String typeName;

    public BookType(String bookType, String typeName) {
        this.bookType = bookType;
        this.typeName = typeName;
    }

    public String getBookType() {
        return bookType;
    }

    public String getTypeName() {
        return typeName;
    }
}
