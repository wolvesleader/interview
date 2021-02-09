package com.quincy.crawler.douban.pojo;

import org.jsoup.select.Elements;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Page
 * @author: quincy
 * @Date: 2021/2/9 下午11:39
 * @History:
 */

public class BookList {

    private int currentPage;

    private Elements elements;

    private String bookListUrl = "https://book.douban.com/tag/小说?start="+0+"&type=T";

    public String getBookListUrl() {
        return "https://book.douban.com/tag/小说?start="+getCurrentPage()+"&type=T";
    }

    public void setBookListUrl(String bookListUrl) {
        this.bookListUrl = "https://book.douban.com/tag/小说?start="+getCurrentPage()+"&type=T";
    }

    public Elements getElements() {
        return elements;
    }

    public void setElements(Elements elements) {
        this.elements = elements;
    }

    public int getCurrentPage() {
        return Math.max(currentPage, 0);
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage < 0){
            this.currentPage = 0;
        }else {
            this.currentPage = currentPage;
        }

    }
}
